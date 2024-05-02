/**
 * This class represents a fleet.
 */
public class Fleet 
{
    /**
     * This Ship represents a Battle Ship.
     */
    public Ship battleShip;

    /**
     * This Ship represents an Aircraft Carrier.
     */
    public Ship aircraftCarrier;

    /**
     * This Ship represents a Cruiser.
     */
    public Ship cruiser;

    /**
     * This Ship represents a Sub.
     */
    public Ship sub;

    /**
     * This Ship represents a Destroyer.
     */
    public Ship destroyer;

    /**
     * Creates a Fleet object. A Fleet has a battleShip, airCraftCarrier, cruiser, sub
     * and destroyer.
     * @param b battleShip
     * @param a airCraftCarrier
     * @param c cruiser
     * @param s sub
     * @param d destroyer
     */
    public Fleet()
    {
        battleShip = new Battleship();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser(); 
        sub = new Sub();
        destroyer = new Destroyer();
    }

    /**
     * Lets ships know if they've been hit and returns true if sunk.
     * @param ship ShipType
     * @return boolean
     */
    public boolean updateFleet(ShipType ship)
    {
        switch(ship)
        {
            case ST_AIRCRAFT_CARRIER:
                return aircraftCarrier.hit();
            case ST_BATTLESHIP:
                return battleShip.hit();
            case ST_CRUISER:
                return cruiser.hit();
            case ST_SUB:
                return sub.hit();
            case ST_DESTROYER:
                return destroyer.hit();
            default:
                return false;
        }
    }

    /**
     * Checks if all ships are sunk and returns true if they are.
     * @return boolean
     */
    public boolean gameOver()
    {
        boolean gameOver;
        if ((battleShip.getSunk() == true) && (aircraftCarrier.getSunk() == true) && (cruiser.getSunk() == true) 
        && (sub.getSunk() == true) && (destroyer.getSunk() == true)) // checks if ships are sunk
        {
            gameOver = true;
        }
        else
        {
            gameOver = false;
        }
        return gameOver;
    }
}
