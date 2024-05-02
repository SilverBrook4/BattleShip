import java.util.Random;
import java.util.ArrayList;

public class UserBoard extends Board
{
    private ArrayList<Move> moves;

    private Random rand;

    public UserBoard(String fileName)
    {
        super(fileName);
        rand = new Random();

        moves = new ArrayList<Move>(100);
        for(int i = 0; i < SIZE; i++)
        {
            for(int j = 0; j < SIZE; j++)
            {
                Move move = new Move(i, j);
                moves.add(move);
            }
        }
    }

    public String[] makeComputerMove()
    {
        boolean moveAvailable = false;
        Move move = null;
        CellStatus cell = null;
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

        boolean sunk;
        switch(cell)
        {
            case AIRCRAFT_CARRIER_HIT:
                sunk = fleet.aircraftCarrier.getSunk();
            case BATTLESHIP_HIT:
                sunk = fleet.battleShip.getSunk();
            case CRUISER_HIT:
                sunk = fleet.cruiser.getSunk();
            case DESTROYER_HIT:
                sunk = fleet.destroyer.getSunk();
            case SUB_HIT:
                sunk = fleet.sub.getSunk();
            default:
                sunk = false;
        }

        if(sunk == true)
        {
            for(ArrayList<CellStatus> i : layout) // sets ship layout
            {
                if(i.contains(cell) == true)
                {
                    for(int j = 0; j > SIZE; j++)
                    {
                        if(i.get(j) == cell)
                        {
                            switch(cell)
                            {
                                case AIRCRAFT_CARRIER_HIT:
                                    i.set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
                                case BATTLESHIP_HIT:
                                    i.set(j, CellStatus.BATTLESHIP_SUNK);
                                case CRUISER_HIT:
                                    i.set(j, CellStatus.CRUISER_SUNK);
                                case DESTROYER_HIT:
                                    i.set(j, CellStatus.DESTROYER_SUNK);
                                case SUB_HIT:
                                    i.set(j, CellStatus.SUB_SUNK);
                                default:
                                    sunk = false;
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

    public Move pickRandomMove()
    {
        int row = rand.nextInt(0, SIZE);
        int col = rand.nextInt(0, SIZE);
        Move move = new Move(row, col);
        return move;
    }

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
        for(int i = 0; i < SIZE; i++)
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
