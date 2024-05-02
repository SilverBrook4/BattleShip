import java.util.ArrayList;

public class Move 
{
    private int row;

    private int col;

    public Move(int r, int c)
    {
        row = r;
        col = c + 1;
    }

    public Move(String s)
    {
        ArrayList<String> collumCompare = new ArrayList<String>(){
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
    
        char rowC = s.charAt(0);
        col = s.charAt(1) - 48;

        for(String i : collumCompare)
        {
            char e = i.charAt(0);
            if (rowC == e)
            {
                row = collumCompare.indexOf(i);
            }
        }
    }

    public int row()
    {
        return row;
    }

    public int col()
    {
        return col;
    }

    public String toString()
    {
        ArrayList<String> collumCompare = new ArrayList<String>(){
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
