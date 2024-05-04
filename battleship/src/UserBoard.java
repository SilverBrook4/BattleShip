/**
 * This is a subclass of the Board class that represents the user's board in the game.
 */
import java.util.Random;
import java.util.ArrayList;

public class UserBoard extends Board
{
    /**
     * This is a list of moves that the computer can make.
     */
    private ArrayList<Move> moves;

    /**
     * This is a random number generator.
     */
    private Random rand;

    /**
     * Constructor for the UserBoard class. It calls the constructor of the superclass, 
     * Board, and passes the fileName parameter to it.
     * @param fileName
     */
    public UserBoard(String fileName)
    {
        super(fileName); 
        rand = new Random();

        moves = new ArrayList<Move>(100); // creates list of moves
        for(int i = 0; i < SIZE; i++)
        {
            for(int j = 0; j < SIZE; j++)
            {
                Move move = new Move(i, j);
                moves.add(move);
            }
        }
    }

    /**
     * This method is used to make a move for the user player. 
     * It applies the move to the board layout and returns a message to be displayed to the player.
     * @return String: the message to be displayed to the player, String: move location
     */
    public String[] makeComputerMove()
    {
        boolean moveAvailable = false;
        Move move = null;
        CellStatus cell = null;

        // randomly selects a move from the list of available moves
        while(moveAvailable == false)
        {
            move = pickRandomMove();
            for(Move i : moves)
            {
                if((i.row() == move.row()) && (i.col() == move.col())) 
                {
                    moves.remove(moves.indexOf(i));
                    cell = applyMoveToLayout(move);
                    moveAvailable = true;
                    break;
                }
            }
            
        }

        // checks if ship is sunk
        boolean sunk;
        switch(cell)
        {
            case AIRCRAFT_CARRIER_HIT:
                sunk = fleet.aircraftCarrier.getSunk();
                break;
            case BATTLESHIP_HIT:
                sunk = fleet.battleShip.getSunk();
                break;
            case CRUISER_HIT:
                sunk = fleet.cruiser.getSunk();
                break;
            case DESTROYER_HIT:
                sunk = fleet.destroyer.getSunk();
                break;
            case SUB_HIT:
                sunk = fleet.sub.getSunk();
                break;
            default:
                sunk = false;
                break;
        }

        // updates ship layout for sunk ships
        if(sunk == true)
        {
            for(int i = 0; i < SIZE; i++)
            {
                if(layout.get(i).contains(cell) == true)
                {
                    for(int j = 0; j > SIZE; j++)
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
            String sunkMessage = "You sunk my battle ship!";
            String[] strArray = {move.toString(), sunkMessage};
            return strArray;
        }
        else
        {
            String[] strArray = {move.toString(), null};
            return strArray;
        }
        
    }

    /**
     * This method is used to pick a random move from the list of available moves.
     * @return Move: a random move from the list of available moves
     */
    public Move pickRandomMove()
    {
        int row = rand.nextInt(0, SIZE);
        int col = rand.nextInt(0, SIZE);
        Move move = new Move(row, col);
        return move;
    }

    /**
     * This method returns a string representation of the user's board.
     * @return String: the string representation of the user's board
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

        String str = "  1 2 3 4 5 6 7 8 9 10 \n";
        for(int i = 0; i < SIZE; i++) // creates string representation of board
        {
            str = str + collumLet.get((i));
            for(int j = 0; j < SIZE; j++)
            {
                CellStatus cs = layout.get(i).get(j);
                String nStr = String.format("%s ", cs.toString().charAt(1));
                str = str + nStr;
            }
            str = str + "\n";
        }
        
        return str;
    }
}
