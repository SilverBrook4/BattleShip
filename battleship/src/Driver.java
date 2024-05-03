import java.util.Random;
import java.util.Scanner;

public class Driver 
{
    public static void main(String[] args)
    {
        System.out.println("Welcome to Battle Ship!!!");

        Game game = new Game();

        Random coin = new Random();

        boolean whosMove;
        if (coin.nextInt(2) == 1)
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
                while(validMove == false)
                {
                    try
                    {
                        System.out.print("Move: ");
                        String moveStr = keyboard.next();
                        String moveData= game.makePlayerMove(moveStr);
                        if(moveData == null)
                        {
                            System.out.println("no ships sank");
                        }
                        else
                        {
                            System.out.println(moveData);
                        }
                        whosMove = false;
                        validMove = true;
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("ERROR: move invalid");
                    }
                }
            }
            else if(whosMove == false) // computers turn
            {
                System.out.println("Computer Turn");
                String[] moveData = game.makeComputerMove();
                if(moveData[1] == null)
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
        }while((game.computerDefeated() == false) && (game.userDefeated() == false));

        if(game.computerDefeated() == true)
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
