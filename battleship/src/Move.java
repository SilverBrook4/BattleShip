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
    
        String[] tokens = s.split("", 2);
        char rowC = tokens[0].toUpperCase().charAt(0);
        String  colC = tokens[1];
        col = Integer.parseInt(colC);

        for(String i : collumCompare)
        {
            if (rowC == i.charAt(0))
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
        return col - 1;
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
