package src.Task1;

import java.util.Random;
import java.util.Scanner;

public class Main1
{
    public static void main (String[] args)
    {
        gamePlay();
    }

    static void gamePlay()
    {
        int number;
        Random rd = new Random();
        number = rd.nextInt(101);
        int guessedNumber = -1;
        Scanner sc = new Scanner(System.in);
        while (number != guessedNumber)
        {
            System.out.println("Zgadnij liczbę");
            try
            {
                guessedNumber = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("To nie jest liczba");
                continue;
            }
            if(guessedNumber < number)
            {
                System.out.println("Za mało!");
            }
            else if (guessedNumber > number)
            {
                System.out.println("Za dużo!");
            }
        }
        System.out.println("Zgadłeś!");

    }
}
