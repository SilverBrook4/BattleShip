import java.util.ArrayList;

public class ComputerBoard extends Board
{
    public ComputerBoard(String fileName)
    {
        super(fileName);
    }

    public String makePlayerMove(Move move)
    {
        CellStatus cell = applyMoveToLayout(move);

        System.out.println(cell);
        boolean sunk;
        switch(cell)
        {
            case AIRCRAFT_CARRIER:
                sunk = fleet.aircraftCarrier.getSunk();
            case BATTLESHIP:
                sunk = fleet.battleShip.getSunk();
            case CRUISER:
                sunk = fleet.cruiser.getSunk();
            case DESTROYER:
                sunk = fleet.destroyer.getSunk();
            case SUB:
                sunk = fleet.sub.getSunk();
            default:
                sunk = false;
        }

        if(sunk == true)
        {
            for(int i = 0; i < SIZE; i++) // updates ship layout fro sunk ships
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
                                case BATTLESHIP_HIT:
                                    layout.get(i).set(j, CellStatus.BATTLESHIP_SUNK);
                                case CRUISER_HIT:
                                    layout.get(i).set(j, CellStatus.CRUISER_SUNK);
                                case DESTROYER_HIT:
                                    layout.get(i).set(j, CellStatus.DESTROYER_SUNK);
                                case SUB_HIT:
                                    layout.get(i).set(j, CellStatus.SUB_SUNK);
                                default:
                                    sunk = false;
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
                String nStr = String.format("%s ", cs.toString().charAt(0));
                str = str + nStr;
            }
            str = str + "\n";
        }
        
        return str;
    }
}
