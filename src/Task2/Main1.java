package src.Task2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main1
{
    public static void main (String[] args)
    {

        int[] userNum = sortNumbers(getNumbers());
        int[] genNum = sortNumbers(randomNumberGenerator());
        printNumbers(userNum);
        printNumbers(genNum);
        win(atLeastThree(userNum, genNum));
    }

    static int[] getNumbers()
    {
        Scanner sc = new Scanner(System.in);
        int[] userNumbers = new int[6];
        for(int i = 0; i < 6; i++)
        {
            System.out.println("Wpisz typowaną liczbę (liczba nr: " + (i + 1) + ")");
            try
            {
                userNumbers[i] = Integer.parseInt(sc.nextLine());
            }
            catch (NumberFormatException e)
            {
                System.out.println("To nie jest liczba");
                i--;
                continue;
            }
            for(int j = 0; j < i; j++)
            {
                if(userNumbers[j] == userNumbers[i])
                {
                    System.out.println("Liczba wpisana poprzednio");
                    i--;
                }
            }
            if(userNumbers[i] < 1 || userNumbers[i] > 49)
            {
                System.out.println("Liczba spoza zakresu");
                i--;
            }
        }
        return userNumbers;
    }

    static int[] sortNumbers (int[] numbers)
    {
         Arrays.sort(numbers);
         return numbers;
    }
    static void printNumbers (int[] numbers)
    {
        for(int i = 0; i < 6; i++)
        {
            System.out.print(numbers[i] + " ");
            System.out.println();
        }
    }
    static int[] randomNumberGenerator ()
    {
        int[] rng = new int[6];
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = i + 1;
        }
        Collections.shuffle(Arrays.asList(arr));
        for (int i = 0; i < 6; i++)
        {
            rng[i] = arr[i];
        }
        return rng;
    }
    static boolean atLeastThree (int[] userNumbers, int[] randomNumbers)
    {
        int i = 0;
        int j = 0;
        int howMany = 0;
        while (i != 6 && j!= 6)
        {
            if(userNumbers[i] == randomNumbers[j])
            {
                howMany++;
                i++;
                j++;
                if(howMany > 2)
                {
                    return true;
                }

            }
            else if (userNumbers[i] > randomNumbers[j])
            {
                j++;
            }
            else
            {
                i++;
            }
        }
        return false;
    }
    static void win (boolean three)
    {
        if (three)
        {
            System.out.println("Trafiłeś!");
        }
    }
}
