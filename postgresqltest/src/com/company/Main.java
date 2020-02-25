package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


	public static void main(String[] args) throws SQLException{

		String db_url = "jdbc:postgresql://db.f4.htw-berlin.de:5432/_herta__uni_db";
		String username = "_herta__uni_db_generic";
		String password = "gen#Passwd5533";
		Connection dbcon = null;
		Statement stmt = null;
		try {
			// Erzeugen einer Verbindung zum DB-System mit der URL dem namen und pwd
			dbcon = DriverManager.getConnection(db_url, username, password);
			// Erzeugen eines Statement-Objekts zum Versenden von Anfragen
			stmt = dbcon.createStatement();

			ResultSet rs= stmt.executeQuery("select * from studenten");
			ResultSetMetaData meda= rs.getMetaData();
            // for example the number of columns
			int num_columns = meda.getColumnCount();
			System.out.println("nb of columns: " + num_columns);

			while (rs.next()){
                for (int i=1; i<=num_columns;i++){
                    System.out.println (rs.getString(i));
                }
            }
			/*
			while (rs.next()){
				System.out.println("Student mit MatrNr: " + rs.getInt("matr_nr"));
				System.out.println("\tName: " + rs.getString("name"));
				System.out.println("\tSemester: " + rs.getInt("semester"));
			}*/
		} catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                } finally {
                if (stmt != null) {
                stmt.close();
                }
                if (dbcon != null) {
                dbcon.close();
                }
                }
                }
                }