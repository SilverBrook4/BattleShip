import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class Board 
{
    public ArrayList<ArrayList<CellStatus>> layout;

    public Fleet fleet;

    public static final int SIZE = 10;

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

                for(ArrayList<CellStatus> i : layout) // sets ship layout
                {
                    if((layout.indexOf(i) >= start.row()) && (layout.indexOf(i) <= end.row()))
                    {
                        for(int j = 0; j > SIZE; j++)
                        {
                            if((j >= start.col()) && (j <= end.col()))
                            {
                                switch(type)
                                {
                                    case "A":
                                        i.set(j, CellStatus.AIRCRAFT_CARRIER);
                                    case "B":
                                        i.set(j, CellStatus.BATTLESHIP);
                                    case "C":
                                        i.set(j, CellStatus.CRUISER);
                                    case "D":
                                        i.set(j, CellStatus.DESTROYER);
                                    case "S":
                                        i.set(j, CellStatus.SUB);
                                    default:
                                }
                            }
                        }
                    }
                }
            }
            inputFile.close();
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
        CellStatus cell = CellStatus.NOTHING;
        for(ArrayList<CellStatus> i : layout) // sets ship layout
        {
            if(move.row() == layout.indexOf(i))
            {
                for(int j = 0; j > SIZE; j++)
                {
                    if(move.col() == j)
                    {
                        cell = i.get(j);
                        switch(cell)
                        {
                            case NOTHING:
                                i.set(j, CellStatus.NOTHING_HIT);
                            case AIRCRAFT_CARRIER:
                                i.set(j, CellStatus.AIRCRAFT_CARRIER_HIT);
                            case BATTLESHIP:
                                i.set(j, CellStatus.BATTLESHIP_HIT);
                            case CRUISER:
                                i.set(j, CellStatus.CRUISER_HIT);
                            case DESTROYER:
                                i.set(j, CellStatus.DESTROYER_HIT);
                            case SUB:
                                i.set(j, CellStatus.SUB_HIT);
                            default:
                        }
                        cell = i.get(j);
                    }
                }
            }
        }
        return cell;
    }

    public boolean moveAvailable(Move move)
    {
        for(ArrayList<CellStatus> i : layout) // sets ship layout
        {
            if(move.row() == layout.indexOf(i))
            {
                for(int j = 0; j > SIZE; j++)
                {
                    if(move.col() == j)
                    {
                        CellStatus cell = i.get(j);
                        switch(cell)
                        {
                            case AIRCRAFT_CARRIER_HIT:
                                return false;
                            case AIRCRAFT_CARRIER_SUNK:
                                return false;
                            case BATTLESHIP_HIT:
                                return false;
                            case BATTLESHIP_SUNK:
                                return false;
                            case CRUISER_HIT:
                                return false;
                            case CRUISER_SUNK:
                                return false;
                            case DESTROYER_HIT:
                                return false;
                            case DESTROYER_SUNK:
                                return false;
                            case SUB_HIT:
                                return false;
                            case SUB_SUNK:
                                return false;
                            default:
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean gameOver()
    {
        return fleet.gameOver();
    }

    public ArrayList<ArrayList<CellStatus>> getLayout()
    {
        return layout;
    }

    public Fleet getFleet()
    {
        return fleet;
    }

}
