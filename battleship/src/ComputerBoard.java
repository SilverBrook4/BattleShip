import java.util.ArrayList;

public class ComputerBoard extends Board
{
    public ComputerBoard(String fileName)
    {
        super(fileName);
    }

    public String makePlayerMove(Move move)
    {
        boolean sunk = false;
        CellStatus type = null;
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
                            case AIRCRAFT_CARRIER:
                                sunk = fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
                                type = CellStatus.AIRCRAFT_CARRIER;
                            case BATTLESHIP:
                                sunk = fleet.updateFleet(ShipType.ST_BATTLESHIP);
                                type = CellStatus.BATTLESHIP;
                            case CRUISER:
                                sunk = fleet.updateFleet(ShipType.ST_CRUISER);
                                type = CellStatus.CRUISER;
                            case DESTROYER:
                                sunk = fleet.updateFleet(ShipType.ST_DESTROYER);
                                type = CellStatus.DESTROYER;
                            case SUB:
                                sunk = fleet.updateFleet(ShipType.ST_SUB);
                                type = CellStatus.SUB;
                            default:
                                sunk = false;
                        }
                    }
                }
            }
        }

        if(sunk == true)
        {
            for(ArrayList<CellStatus> i : layout) // sets ship layout
            {
                if(i.contains(type) == true)
                {
                    for(int j = 0; j > SIZE; j++)
                    {
                        if(i.get(j) == type)
                        {
                            switch(type)
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
            String sunkMessage = "You sank my battle ship!";
            return sunkMessage;
        }
        else
        {
            return null;
        }
    }

    public String toString()
    {
        String str = "";
        for(ArrayList<CellStatus> i : layout)
        {
            for(int j = 0; j < SIZE; j++)
            {
                CellStatus cs = i.get(j);
                String nStr = String.format("%s ", cs.toString().charAt(0));
                str = str + nStr;
            }
            str = str + "\n";
        }
        
        return str;
    }
}
