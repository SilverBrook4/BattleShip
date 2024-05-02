import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class Board 
{
    private ArrayList<ArrayList<CellStatus>> layout;

    private Fleet fleet;

    private static final int SIZE = 10;

    public Board(String fileName)
    {
        // initializes board & sets cells to NOTHING
        layout = new ArrayList<ArrayList<CellStatus>>(10);
        for(int i = 0; i < SIZE; i++)
        {
            layout.add(new ArrayList<CellStatus>(10));
            for(int j = 0; j < SIZE; j++)
            {
                layout.get(i).add(CellStatus.NOTHING);
            }
        }

        fleet = new Fleet();

        try 
        { 
            File setupFile = new File(fileName);
            Scanner inputFile = new Scanner(setupFile);
            while(inputFile.hasNext() == true)
            {
                String data = inputFile.nextLine(); // gets data and tokenizes it
                String[] tokens = data.split(" ");
                String type = tokens[0];
                String startStr = tokens[1];
                String endStr = tokens[2];

                Move start = new Move(startStr);
                Move end = new Move(endStr);

                switch(type)
                {
                    case "A": // Aircraft Carrier
                        for(ArrayList<CellStatus> i : layout)
                        {
                            if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                            {
                                for(int j = 0; j > SIZE; j++)
                                {
                                    if((j >= start.col()) && (j <= end.col()))
                                    {
                                        i.set(j, CellStatus.AIRCRAFT_CARRIER);
                                    }

                                }
                            }
                        }

                    case "B": // Battle Ship
                        for(ArrayList<CellStatus> i : layout)
                        {
                            if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                            {
                                for(int j = 0; j > SIZE; j++)
                                {
                                    if((j >= start.col()) && (j <= end.col()))
                                    {
                                        i.set(j, CellStatus.BATTLESHIP);
                                    }

                                }
                            }
                        }
                    case "C": // Cruiser
                        for(ArrayList<CellStatus> i : layout)
                        {
                            if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                            {
                                for(int j = 0; j > SIZE; j++)
                                {
                                    if((j >= start.col()) && (j <= end.col()))
                                    {
                                        i.set(j, CellStatus.CRUISER);
                                    }

                                }
                            }
                        }
                    case "D": // Destroyer
                        for(ArrayList<CellStatus> i : layout)
                        {
                            if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                            {
                                for(int j = 0; j > SIZE; j++)
                                {
                                    if((j >= start.col()) && (j <= end.col()))
                                    {
                                        i.set(j, CellStatus.DESTROYER);
                                    }

                                }
                            }
                        }
                    case "S": // Sub
                        for(ArrayList<CellStatus> i : layout)
                        {
                            if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                            {
                                for(int j = 0; j > SIZE; j++)
                                {
                                    if((j >= start.col()) && (j <= end.col()))
                                    {
                                        i.set(j, CellStatus.SUB);
                                    }

                                }
                            }
                        }
                    default:
                        System.out.println("Invalid Ship Type");
              }

            }
        }
        catch(IOException e)
        { // Throws if file is not found
            System.out.println("IO Exception: file not found");
            System.out.println("terminating");
            System.exit(0);
        }

    }

    public CellStatus applyMoveToLayout(Move move)
    {

    }

    public boolean moveAvailable(Move move)
    {

    }

    public boolean gameOver()
    {

    }

    public ArrayList<ArrayList<CellStatus>> getLayout()
    {

    }

    public Fleet getFleet()
    {

    }

}
