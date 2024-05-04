/**
 * This class is a subclass of the Board class that represents the computer's board in the game of Battleship.
 */
import java.util.ArrayList;

public class ComputerBoard extends Board
{
    /**
     * Constructor for the ComputerBoard class. It calls the constructor of the superclass, Board, and passes the fileName parameter to it.
     * @param fileName
     */
    public ComputerBoard(String fileName)
    {
        super(fileName);
    }

    /**
     * This method is used to make a move for the computer player. It randomly selects a move from the list of available moves and applies it to the board layout.
     * @param move Move: the move to be made
     * @return String: the message to be displayed to the player
     */
    public String makePlayerMove(Move move)
    {
        CellStatus cell = applyMoveToLayout(move); // applies move to layout

        boolean sunk; // checks if ship is sunk
        switch(cell)
        {
            case AIRCRAFT_CARRIER:
                sunk = fleet.aircraftCarrier.getSunk();
                break;
            case BATTLESHIP:
                sunk = fleet.battleShip.getSunk();
                break;
            case CRUISER:
                sunk = fleet.cruiser.getSunk();
                break;
            case DESTROYER:
                sunk = fleet.destroyer.getSunk();
                break;
            case SUB:
                sunk = fleet.sub.getSunk();
                break;
            default:
                sunk = false;
                break;
        }

        if(sunk == true) // updates ship layout for sunk ships
        {
            for(int i = 0; i < SIZE; i++)
            {
                if(layout.get(i).contains(cell))
                {
                    for(int j = 0; j < SIZE; j++)
                    {
                        if(layout.get(i).get(j) == cell)
                        {
                            switch(cell)
                            {
                                case AIRCRAFT_CARRIER_HIT:
                                    layout.get(i).set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                                    break;
                                case BATTLESHIP_HIT:
                                    layout.get(i).set(j, CellStatus.BATTLESHIP_SUNK);
                                    break;
                                case CRUISER_HIT:
                                    layout.get(i).set(j, CellStatus.CRUISER_SUNK);
                                    break;
                                case DESTROYER_HIT:
                                    layout.get(i).set(j, CellStatus.DESTROYER_SUNK);
                                    break;
                                case SUB_HIT:
                                    layout.get(i).set(j, CellStatus.SUB_SUNK);
                                    break;
                                default:
                                    sunk = false;
                                    break;
                            }
                        }
                    }
                }
            }
            String sunkMessage = "You sank my battle ship!";
            return sunkMessage;
        }
        else
        {
            return null;
        }
    }

    /**
     * This method returns a string representation of the computer's board.
     * @return String: the string representation of the computer's board
     */
    @Override
    public String toString()
    {
        ArrayList<String> collumLet = new ArrayList<String>(){
            {
                add("A ");
                add("B ");
                add("C ");
                add("D ");
                add("E ");
                add("F ");
                add("G ");
                add("H ");
                add("I ");
                add("J ");
            }
        };

        String str = "  1 2 3 4 5 6 7 8 9 10 \n"; // creates string representation of board
        for(int i = 0; i < SIZE; i++)
        {
            str = str + collumLet.get((i));
            for(int j = 0; j < SIZE; j++)
            {
                CellStatus cs = layout.get(i).get(j);
                String nStr = String.format("%s ", cs.toString().charAt(0));
                str = str + nStr;
            }
            str = str + "\n";
        }
        
        return str;
    }
}
