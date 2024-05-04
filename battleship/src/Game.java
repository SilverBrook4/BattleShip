/**
 * This class represents the Game and takes care of a lot of the board handling.
 */
public class Game 
{
    /**
     * This is the computer's board.
     */
    private ComputerBoard computer;

    /**
     * This is the player's board.
     */
    private UserBoard player;

    /**
     * This is the file name for the computer's fleet.
     */
    private static final String COMP_FLEET = "compFleet.txt";

    /**
     * This is the file name for the player's fleet.
     */
    private static final String USER_FLEET = "userFleet.txt";

    /**
     * This is the constructor for the Game class. It initializes the computer and player boards.
     */
    public Game()
    {
        computer = new ComputerBoard(COMP_FLEET);
        player = new UserBoard(USER_FLEET);
    }

    /**
     * This method is used to make a move for the computer player.
     * @return String[]: the move data
     */
    public String[] makeComputerMove()
    {

        String [] moveData = {};
        moveData = player.makeComputerMove();
        return moveData;
    }

    /**
     * This method is used to make a move for the player.
     * @param input String: the move to be made
     * @return String: the move data
     */
    public String makePlayerMove(String input)
    {
        Move move = new Move(input);
        String moveData = computer.makePlayerMove(move);
        return moveData;
    }

    /**
     * This method is used to determine if the computer has been defeated.
     * @return boolean: true if the computer has been defeated, false otherwise
     */
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

    /**
     * This method is used to determine if the player has been defeated.
     * @return boolean: true if the player has been defeated, false otherwise
     */
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

    /**
     * This method is used to return the string representation of the game.
     * @return String: the string representation of the game
     */
    @Override
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
