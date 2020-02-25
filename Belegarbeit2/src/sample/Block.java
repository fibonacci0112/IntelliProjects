package sample;

/**
 * Created by Nico on 21.12.2016.
 */
public class Block
{


    private int[][] blockfeld;

    public int[][] getBlockfeld()
    {
        return blockfeld;
    }

    public Block(int auswahl)
    {
        switch (auswahl)
        {
            case 0:
                blockfeld = new int[][]{        //probieren ob ohne nullen möglich
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,0,0,0},
                        {1,0,0,0}};
                break;

            case 1:
                blockfeld = new int[][]{
                        {1, 1},
                        {1, 1},};
                break;

            case 2:
                blockfeld = new int[][]{
                        {1, 0, 0},
                        {1, 1, 0},
                        {0, 1, 0}};
                break;

            case 3:
                blockfeld = new int[][]{
                        {0, 1, 0},
                        {1, 1, 0},
                        {0, 1, 0}};
                break;

            case 4:
                blockfeld = new int[][]{
                        {1, 0, 0},
                        {1, 1, 1},
                        {0, 0, 0}};
                break;

        }
    }

    public void rotieren()
    {
        //this.blockfeld muss rotiert werden
    }

}
