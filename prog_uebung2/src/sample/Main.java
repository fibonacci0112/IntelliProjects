package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application
{

    Group g;
    Stage searchstage;
    TextArea txtarea;
    TextArea searchbar;
    Button srchbtn;
    TextArea replacebar;
    Button replacebtn;
    ToggleButton uppercase;
    Group srchg;
    MenuBar menuBar;
    Menu menuFile;
    Menu menuEdit;
    MenuItem menuDat;
    MenuItem menuSafe;
    MenuItem menuLoad;
    MenuItem menuclose;
    MenuItem menusearch;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Text Editor");
        primaryStage.setScene(new Scene(root, 700, 700));
        primaryStage.setWidth(700);
        primaryStage.setHeight(700);
        txtarea = new TextArea("");
        txtarea.setLayoutY(30);
        txtarea.setPrefHeight(primaryStage.getHeight() - 100);
        txtarea.setPrefWidth(primaryStage.getWidth() - 30);
        menuBar = new MenuBar();
        menuFile = new Menu("Datei");
        menuEdit = new Menu("Edit");
        menuBar.getMenus().addAll(menuFile, menuEdit);
        menuDat = new MenuItem("Datei neu");
        menuSafe = new MenuItem("Datei speichern unter");
        menuLoad = new MenuItem("Datei öffnen");
        menuclose = new MenuItem("Programm beenden");
        menusearch = new MenuItem("Suchen");
        menuFile.getItems().addAll(menuDat, menuSafe, menuLoad, menuclose);
        menuEdit.getItems().add(menusearch);
        menuclose.setOnAction(t -> System.exit(0));
        menusearch.setOnAction(event ->
        {
            searchstage = new Stage();
            searchstage.setTitle("Suchen");
            searchstage.setHeight(230);
            searchstage.setWidth(400);
            searchbar = new TextArea("");
            searchbar.setPrefWidth(searchstage.getWidth() - 20);
            searchbar.setPrefHeight(50);

            replacebar = new TextArea("");
            replacebar.setPrefRowCount(1);
            replacebar.setLayoutY(90);
            replacebar.setPrefHeight(40);
            replacebar.setPrefWidth(searchstage.getWidth() - 20);

            replacebtn = new Button("Ersetzen");
            replacebtn.setLayoutY(140);

            srchbtn = new Button("Suche");
            srchbtn.setLayoutY(60);

            uppercase = new ToggleButton("Check for Uppercase");
            uppercase.setLayoutY(60);
            uppercase.setLayoutX(50);

            srchg = new Group();
            srchg.getChildren().addAll(searchbar, srchbtn, replacebar, replacebtn, uppercase);
            searchstage.setScene(new Scene(srchg));
            searchstage.show();

            srchbtn.setOnAction(event1 ->
                    {
                        if (this.suche())
                        {
                            searchstage.close();

                        }
                    }
            );
            replacebtn.setOnAction(event2 ->
                    {
                        if (this.suche())
                        {
                            txtarea.replaceSelection(replacebar.getText());
                            searchstage.close();
                        }
                    }
            );
        });

        g = new Group();
        g.getChildren().addAll(txtarea, menuBar);
        primaryStage.setScene(new Scene(g));
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    public boolean suche()
    {

        if (searchbar.getText() != null || searchbar.getText() != "")
        {
            String string = searchbar.getText();
            if (!uppercase.isSelected())
            {
                if (txtarea.getText().toLowerCase().contains(string.toLowerCase()))
                {
                    txtarea.requestFocus();
                    txtarea.selectRange(txtarea.getText().toLowerCase().indexOf(string.toLowerCase()), txtarea.getText().toLowerCase().indexOf(string.toLowerCase()) + string.length());
                    return true;
                } else
                {
                    searchbar.setText("Not Found");
                    searchbar.requestFocus();
                    searchbar.selectAll();
                    return false;
                }
            } else
            {
                if (txtarea.getText().contains(string))
                {
                    txtarea.requestFocus();
                    txtarea.selectRange(txtarea.getText().indexOf(string), txtarea.getText().indexOf(string) + string.length());
                    return true;
                } else
                {
                    searchbar.setText("Not Found");
                    searchbar.requestFocus();
                    searchbar.selectAll();
                    return false;
                }
            }
        }
        return false;
    }
}

