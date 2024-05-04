/**
 * This class represents a board in the game.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class Board 
{
    /**
     * The layout of the board.
     */
    public ArrayList<ArrayList<CellStatus>> layout;

    /**
     * The fleet of the board.
     */
    public Fleet fleet;

    /**
     * The size of the board.
     */
    public static final int SIZE = 10;

    /**
     * Constructs a board with a given file name.
     * @param fileName String: the name of the file
     */
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

        // initializes fleet
        fleet = new Fleet();

        // reads file and sets ship layout
        try 
        { 
            File setupFile = new File(fileName); // reads file
            Scanner inputFile = new Scanner(setupFile);
            while(inputFile.hasNext() == true)
            {
                String data = inputFile.nextLine(); // gets data and tokenizes it
                String[] tokens = data.split(" ");
                String type = tokens[0];
                String startStr = tokens[1];
                String endStr = tokens[2];

                Move start = new Move(startStr); // creates move objects
                Move end = new Move(endStr);

                for(int i = 0; i < SIZE; i++) // sets ship layout
                {
                    if((i >= start.row()) && (i <= end.row()))
                    {
                        for(int j = 0; j < SIZE; j++)
                        {
                            if((j >= start.col()) && (j <= end.col()))
                            {
                                switch(type)
                                {
                                    case "A":
                                        layout.get(i).set(j, CellStatus.AIRCRAFT_CARRIER);
                                        break;
                                    case "B":
                                        layout.get(i).set(j, CellStatus.BATTLESHIP);
                                        break;
                                    case "C":
                                        layout.get(i).set(j, CellStatus.CRUISER);
                                        break;
                                    case "D":
                                        layout.get(i).set(j, CellStatus.DESTROYER);
                                        break;
                                    case "S":
                                        layout.get(i).set(j, CellStatus.SUB);
                                        break;
                                    default:
                                        break;
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

    /**
     * Applies a move to the layout.
     * @param move Move: the move to apply
     * @return CellStatus: the status of the cell
     */
    public CellStatus applyMoveToLayout(Move move)
    {
        CellStatus cell = CellStatus.NOTHING;
        CellStatus value = CellStatus.NOTHING;
        for(int i = 0; i < SIZE; i++) // applies move to layout
        {
            if(move.row() == i)
            {
                for(int j = 0; j < SIZE; j++)
                {
                    if(move.col() == j)
                    {
                        cell = layout.get(i).get(j);

                        switch(cell)
                        {
                            case NOTHING:
                                layout.get(i).set(j, CellStatus.NOTHING_HIT);
                                value = CellStatus.NOTHING;
                                break;
                            case AIRCRAFT_CARRIER:
                                layout.get(i).set(j, CellStatus.AIRCRAFT_CARRIER_HIT);
                                fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
                                value = CellStatus.AIRCRAFT_CARRIER;
                                break;
                            case BATTLESHIP:
                                layout.get(i).set(j, CellStatus.BATTLESHIP_HIT);
                                fleet.updateFleet(ShipType.ST_BATTLESHIP);
                                value = CellStatus.BATTLESHIP;
                                break;
                            case CRUISER:
                                layout.get(i).set(j, CellStatus.CRUISER_HIT);
                                fleet.updateFleet(ShipType.ST_CRUISER);
                                value = CellStatus.CRUISER;
                                break;
                            case DESTROYER:
                                layout.get(i).set(j, CellStatus.DESTROYER_HIT);
                                fleet.updateFleet(ShipType.ST_DESTROYER);
                                value = CellStatus.DESTROYER;
                                break;
                            case SUB:
                                layout.get(i).set(j, CellStatus.SUB_HIT);
                                fleet.updateFleet(ShipType.ST_SUB);
                                value = CellStatus.SUB;
                                break;
                            default:
                                System.out.println("Already Bombed: move forfieted");
                                break;
                        }
                    }
                }
            }
        }
        return value;
    }

    /**
     * Checks if a move is available.
     * @param move Move: the move to check
     * @return boolean: true if move is available, false otherwise
     */
    public boolean moveAvailable(Move move)
    {
        boolean status = false;
        for(int i = 0; i < SIZE; i++) // checks cell status of cells
        {
            if(move.row() == i)
            {
                for(int j = 0; j < SIZE; j++)
                {
                    if(move.col() == j)
                    {
                        CellStatus cell = layout.get(i).get(j);
                        System.out.println(cell);
                        switch(cell)
                        {
                            case NOTHING_HIT:
                                status = false;
                                break;
                            case AIRCRAFT_CARRIER_HIT:
                                status = false;
                                break;
                            case AIRCRAFT_CARRIER_SUNK:
                                status = false;
                                break;
                            case BATTLESHIP_HIT:
                                status = false;
                                break;
                            case BATTLESHIP_SUNK:
                                status = false;
                                break;
                            case CRUISER_HIT:
                                status = false;
                                break;
                            case CRUISER_SUNK:
                                status = false;
                                break;
                            case DESTROYER_HIT:
                                status = false;
                                break;
                            case DESTROYER_SUNK:
                                status = false;
                                break;
                            case SUB_HIT:
                                status = false;
                                break;
                            case SUB_SUNK:
                                status = false;
                                break;
                            default:
                                status = true;
                                break;
                        }
                    }
                }
            }
        }
        return status;
    }

    /**
     * Checks if the game is over.
     * @return boolean: true if game is over, false otherwise
     */
    public boolean gameOver()
    {
        return fleet.gameOver();
    }

    /**
     * Gets the layout of the board.
     * @return ArrayList<ArrayList<CellStatus>>: the layout of the board
     */
    public ArrayList<ArrayList<CellStatus>> getLayout()
    {
        return layout;
    }

    /**
     * Gets the fleet of the board.
     * @return Fleet: the fleet of the board
     */
    public Fleet getFleet()
    {
        return fleet;
    }

}
