package sample;

import java.io.*;
import java.util.Stack;

/**
 * Implementierung von Graphen mit gerichteten und gewichteten Kanten
 * Created by Nico on 11.01.2017.
 */
public class Graph
{
    public Node[] nodes;
    public int[][] joints;
    public int nodecount;
    public Stack<Node> searchpath;
    public Stack<Node> finalpath;


    public Graph()
    {
        this.nodes = new Node[100];
        this.joints = new int[100][100];
        for (int i = 0; i < 100; i++)
        {
            nodes[i] = new Node("");
            for (int j = 0; j < 100; j++)
            {
                joints[i][j] = 0;
            }
        }
        searchpath = new Stack<>();
        finalpath = new Stack<>();
    }

    /**
     * teilt einen eingegebenen String auf und erzeugt Knoten und Kanten daraus. True wenn erfolgreich
     *
     * @param s
     * @return boolean
     */
    public boolean graphBuilder(String s)
    {
        try
        {
            if (s.contains("-"))
            {
                String[] lines = s.split("-");
                depthFirstSearch(nodes[findNode(lines[0])], nodes[findNode(lines[1])]);
            } else
            {
                String[] lines = s.split(";");
                for (int i = 0; i < lines.length; i++)
                {
                    String[] chars = lines[i].split(",");
                    if (chars.length == 1)
                    {
                        newNode(chars[0]);
                    } else
                    {
                        createJoint(chars[0], chars[1], Integer.valueOf(chars[2]));
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println("Fehler bei der Eingabe" + e.toString());
            return false;
        }
        return true;
    }

    /**
     * erzeugt einen neuen Knoten mit Namen und Darstellungskoordinaten
     *
     * @param name
     */
    public void newNode(String name)
    {
        if (findNode(name) != -1)
        {
            System.out.println("name existiert bereits");
            return;
        }
        nodes[nodecount] = new Node(name);
        nodecount++;
    }

    /**
     * loescht einen mit Namen ausgewählten Knoten
     *
     * @param name
     */
    public void deleteNode(String name)
    {
        int noNr = findNode(name);
        for (int i = 0; i < nodecount; i++)
        {
            joints[noNr][i] = 0;
            joints[i][noNr] = 0;
        }

        for (int i = noNr; i < nodecount; i++)
        {
            nodes[i] = nodes[i + 1];
        }

        for (int i = noNr; i < nodecount; i++)
        {
            for (int j = noNr; j < nodecount; j++)
            {
                joints[i][j] = joints[i + 1][j + 1];

            }
        }
        nodecount--;
    }

    /**
     * traegt Kante in die Adjazenzmatrix ein
     *
     * @param name1
     * @param name2
     * @param value
     */
    public void createJoint(String name1, String name2, int value)
    {
        joints[findNode(name1)][findNode(name2)] = value;
    }

    /**
     * findet Node anhand des Namens
     *
     * @param name
     * @return
     */
    public int findNode(String name)
    {
        for (int i = 0; i < nodecount; i++)
        {
            if (nodes[i].name.equals(name))
            {
                return i;
            }
        }
        return -1;
    }

    /**
     * Liefert alle Knoten zurueck auf die n eine Kante hat
     *
     * @param n
     * @return
     */
    public Node[] findNeighbours(Node n)
    {
        int nodeIndex = findNode(n.name);
        int count = 0;
        Node[] neighbours = new Node[nodecount];

        if (nodeIndex != -1)
        {
            for (int j = 0; j < nodecount; j++)
            {
                if (joints[nodeIndex][j] != 0)
                {
                    neighbours[count] = nodes[j];
                    count++;
                }
            }
        }
        return neighbours;
    }

    /**
     * Zur Ausgabe des Graphs auf einer Konsole
     */
    public void sout()
    {
        for (int i = 0; i < nodecount; i++)
        {
            System.out.print(nodes[i].name + ", ");
        }
        System.out.println();
        System.out.println();

        for (int i = 0; i < nodecount; i++)
        {
            for (int j = 0; j < nodecount; j++)
            {
                System.out.print(joints[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Schreibt den Aktuellen Graph in eine Textdatei
     */
    public void writeToFile()
    {
        BufferedWriter bw;
        String s;
        try
        {
            bw = new BufferedWriter(new FileWriter("save.txt"));
            for (int i = 0; i < nodecount; ++i)
            {
                s = nodes[i].name + ",";
                bw.write(s);
            }
            bw.write(";");

            for (int i = 0; i < nodecount; ++i)
            {
                for (int j = 0; j < nodecount; j++)
                {
                    s = String.valueOf(joints[i][j] + ",");
                    bw.write(s);
                }
                bw.write(";");
            }

            bw.close();
        } catch (IOException e)
        {
            System.out.println("Fehler");
        }

    }

    /**
     * Liest einen Graphen aus einer Textdatei ein
     */
    public void readFromFile()
    {
        BufferedReader br;
        String s;
        try
        {
            this.nodes = new Node[100];
            this.joints = new int[100][100];
            for (int i = 0; i < 100; i++)
            {
                nodes[i] = new Node("");
                for (int j = 0; j < 100; j++)
                {
                    joints[i][j] = 0;
                }
            }
            br = new BufferedReader(new FileReader("save.txt"));
            s = br.readLine();
            String[] lines = s.split(";");
            String[] chars = lines[0].split(",");
            for (int i = 0; i < chars.length; i++)
            {
                nodes[i].name = chars[i];
            }
            nodecount = chars.length;
            for (int i = 1; i < lines.length; i++)
            {
                chars = lines[i].split(",");
                for (int j = 0; j < chars.length; j++)
                {
                    joints[i - 1][j] = Integer.parseInt(chars[j]);
                }
            }

            br.close();
        } catch (IOException e)
        {
            System.out.println("Fehler");
        }
    }

    /**
     * sucht den Kuerzesten Weg zwischen zwei Kanten im Graph
     *
     * @param start
     * @param end
     */
    public void depthFirstSearch(Node start, Node end)
    {
        searchpath.push(start);

        Node[] neighbours = findNeighbours(start);
        for (int i = 0; i < neighbours.length; i++)
        {
            if (neighbours[i] != null && neighbours[i].equals(end))
            {
                searchpath.push(end);
                end.visited = true;
                for (int j = 0; j < searchpath.size(); j++)
                {
                    finalpath.add(searchpath.get(j));
                }
                return;
            }
            Node n = neighbours[i];
            if (n != null && !n.visited)
            {
                n.visited = true;
                depthFirstSearch(n, end);
                searchpath.pop();
            }
        }
    }
}
