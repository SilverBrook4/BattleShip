/**
 * This class a move on the board.
 */
import java.util.ArrayList;

public class Move 
{
    /**
     * The row of the move.
     */
    private int row;

    /**
     * The column of the move.
     */
    private int col;

    /**
     * Constructor for the Move class. The row # and column # are passed in as parameters.
     * @param r int: row
     * @param c int: col
     */
    public Move(int r, int c)
    {
        row = r;
        col = c + 1;
    }

    /**
     * Constructor for the Move class. The move is passed in as a string.
     * @param s String: move
     */
    public Move(String s)
    {
        ArrayList<String> collumCompare = new ArrayList<String>() // letters to compare to input for indeces
        {
            {
                add("A");
                add("B");
                add("C");
                add("D");
                add("E");
                add("F");
                add("G");
                add("H");
                add("I");
                add("J");
            }
        };
    
        String[] tokens = s.split("", 2); // splits input into row and column
        char rowC = tokens[0].toUpperCase().charAt(0);
        String  colC = tokens[1];
        col = Integer.parseInt(colC);

        for(int i = 0; i < 10; i++)
        {
            if (rowC == collumCompare.get(i).charAt(0))
            {
            row = i;
            }
        }
    }

    /**
     * Returns the row of the move.
     * @return int: row
     */
    public int row()
    {
        return row;
    }

    /**
     * Returns the column of the move.
     * @return int: col
     */
    public int col()
    {
        return col - 1;
    }

    /**
     * Returns the move as a string.
     * @return String: move in readable format
     */
    @Override
    public String toString()
    {
        ArrayList<String> collumCompare = new ArrayList<String>() // letters to compare to input for indeces
        { 
            {
                add("A");
                add("B");
                add("C");
                add("D");
                add("E");
                add("F");
                add("G");
                add("H");
                add("I");
                add("J");
            }
        };
        
        return String.format("%s%d", collumCompare.get(row), col);
    }
}
