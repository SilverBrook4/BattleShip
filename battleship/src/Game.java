public class Game 
{
    private ComputerBoard computer;

    private UserBoard player;

    private static final String COMP_FLEET = "compFleet.txt";

    private static final String USER_FLEET = "userFleet.txt";

    public Game()
    {
        computer = new ComputerBoard(COMP_FLEET);
        player = new UserBoard(USER_FLEET);
    }

    public String[] makeComputerMove()
    {

        String [] moveData = {};
        moveData = player.makeComputerMove();
        return moveData;
    }

    public String makePlayerMove(String input)
    {
        Move move = new Move(input);
        String moveData = computer.makePlayerMove(move);
        return moveData;
    }

    public boolean computerDefeated()
    {
        if(computer.gameOver() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean userDefeated()
    {
        if(player.gameOver() == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String toString()
    {
        return String.format("--------------------\n" +
                            "Computer Board:     \n\n" +
                                    "%s\n\n" +
                                    "Player Board:       \n\n" +
                                    "%s\n\n" +
                                    "********************\n", computer.toString(), player.toString());
    }
}
