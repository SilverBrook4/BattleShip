import java.util.Random;
import java.util.Scanner;

public class Driver 
{
    public static void main(String[] args)
    {
        Game game = new Game();

        Random coin = new Random();

        boolean whosMove;
        if (coin.nextInt(2) == 1)
        {
            whosMove = true; // player move
        }
        else
        {
            whosMove = false; // computer move
        }

        System.out.print(game);

        while((game.computerDefeated() == false) && (game.userDefeated() == false))
        {
            if(whosMove == true) // players turn
            {
                Scanner keyboard = new Scanner(System.in);
                System.out.print("Move: ");
                String moveStr = keyboard.next();
                String moveData= game.makePlayerMove(moveStr);
                if(moveData != null)
                {
                    System.out.println(moveData);
                }
                else
                {
                    System.out.println("nothing hit");
                }
                whosMove = false;
            }
            else if(whosMove == false) // computers turn
            {
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
        }

    }

}
