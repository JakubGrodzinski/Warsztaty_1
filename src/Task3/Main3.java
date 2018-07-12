package src.Task3;

import java.util.Scanner;

public class Main3
{
    public static void main(String[] args)
    {
        computerGuesses();
    }

    static void computerGuesses ()
    {

        int guess = 0;
        String userResponse;
        int min = 0;
        int max = 1000;

        Scanner sc = new Scanner(System.in);
        System.out.println("Pomyśl liczbę od 0 do 1000, a ja zgadnę ją w max. 10 próbach");

        while(true)
        {

                guess = (min + max) / 2;
                System.out.println("Zgaduję:" + guess);

            System.out.println("Zgadłem? (tak/nie)");
            userResponse = sc.nextLine();
            if(!userResponse.equals("tak") && !userResponse.equals("nie"))
            {
                System.out.println("Wpisz \"tak\" lub \"nie\"");

            }
            else if (userResponse.equals("tak"))
            {
                System.out.println("Wygrałem!");
                break;
            }
            else
            {
                System.out.println("Za dużo? (tak/nie)");
                userResponse = sc.nextLine();
                if(!userResponse.equals("tak") && !userResponse.equals("nie"))
                {
                    System.out.println("Wpisz \"tak\" lub \"nie\"");

                }
                else if (userResponse.equals("tak"))
                {
                    max = guess;
                }
                else
                {
                    System.out.println("Za mało? (tak/nie)");
                    userResponse = sc.nextLine();
                    if(!userResponse.equals("tak") && !userResponse.equals("nie"))
                    {
                        System.out.println("Wpisz \"tak\" lub \"nie\"");

                    }
                    else if (userResponse.equals("tak"))
                    {
                        min = guess;
                    }
                    else
                    {
                        System.out.println("Nie oszukuj!");
                    }

                }

            }


        }
    }
}
