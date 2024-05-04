import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver 
{
    private static final ArrayList<String> COLLUM_COMPARE = new ArrayList<String>(){
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

    public static void main(String[] args)
    {
        System.out.println("Welcome to Battle Ship!!!");

        Game game = new Game();

        Random coin = new Random();

        boolean whosMove; 
        if (coin.nextInt(2) == 1) // coin toss
        {
            whosMove = true; // player move
            System.out.println("Coin Toss Won: you go first!");
        }
        else
        {
            whosMove = false; // computer move
            System.out.println("Coin Toss Lost: computer goes first :(");
        }

        System.out.print(game);

        Scanner keyboard = new Scanner(System.in);

        do 
        {
            if(whosMove == true) // players turn
            {
                System.out.println("Your Turn");
                boolean validMove = false;
                while(validMove == false)  // loop to check if move is valid
                {
                    try
                    {
                        String moveStr = "";
                        while(validMove == false)
                        {
                            System.out.print("Move: "); // get move from player
                            moveStr = keyboard.next();
                            String[] tokens = moveStr.split("", 2); // split move into row and column
                            char rowC = tokens[0].toUpperCase().charAt(0);
                            String  colC = tokens[1];
                            int col = Integer.parseInt(colC);
                            if((col >= 1) && (col <= 10)) // check if column is valid
                            {
                                for(int i = 0; i < 10; i++)
                                {
                                    if (rowC == COLLUM_COMPARE.get(i).charAt(0))
                                    {
                                        int row = i;
                                        if((row >= 0) && (row <= 9)) // check if row is valid
                                        {
                                            validMove = true;
                                        }
                                        else
                                        {
                                            System.out.println("ERROR: move invalid");
                                        }
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("ERROR: move invalid");
                            }
                        }
                        String moveData = game.makePlayerMove(moveStr);

                        if(moveData == null) // check if ship sank
                        {
                            System.out.println("no ships sank");
                        }
                        else
                        {
                            System.out.println(moveData);
                        }
                        whosMove = false;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("ERROR: move invalid");
                        validMove = false;
                    }
                }
            }
            else if(whosMove == false) // computers turn
            {
                System.out.println("Computer Turn"); // get move from computer
                String[] moveData = game.makeComputerMove(); 
                if(moveData[1] == null) // check if ship sank
                {
                    System.out.println(moveData[0]);
                    System.out.println("nothing hit");
                }
                else
                {
                    System.out.println(moveData[0]);
                    System.out.println(moveData[1]);
                }
                whosMove = true;
            }
            System.out.print(game);

            // pause between moves
            if(whosMove == true) 
            {
                System.out.print("press ENTER something to continue:");
                keyboard.next();
            }
        }while((game.computerDefeated() == false) && (game.userDefeated() == false)); // check if game is over

        if(game.computerDefeated() == true) // check who won
        {
            System.out.println("You Win!");
        }
        else if(game.userDefeated() == true)
        {
            System.out.println("Computer Wins :(");
        }
        
        keyboard.close();
    }

}
