package src.RollADice;

import java.util.Random;

public class Main4
{
    public static void main(String[] args)
    {
        System.out.println(roll("2D1+2"));
    }

    static int roll (String diceCode)
    {
        int multiplier = 0;
        int addToMultiplier = 0;
        int typeofDice = 0;
        int addToTypeOfDice = 0;
        int lastNumber = 0;
        int addToLastNumber = 0;
        int i = 0;
        int result;
        boolean isTherePlus = false;
        boolean isThereMinus = false;
        char x = ' ';
        Random rn = new Random();
        while (x != 'D')
        {
            x = diceCode.charAt(i);
            if(x != 'D')
            {
                try
                {
                    addToMultiplier = Integer.parseInt(String.valueOf(x));
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Nieprawidłowy format danych");
                    throw e;
                }
                multiplier = multiplier * 10 + addToMultiplier;
            }
            i++;
        }
        while(i < diceCode.length() && x != '+' && x!= '-')
        {
            x = diceCode.charAt(i);
            if (x != '+' && x != '-')
            {
                try
                {
                    addToTypeOfDice = Integer.parseInt(String.valueOf(x));
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Nieprawidłowy format danych");
                    throw e;
                }
                typeofDice = typeofDice * 10 + addToTypeOfDice;
                i++;
            }
        }
        if (i < diceCode.length())
        {
            if(diceCode.charAt(i) == '+')
            {
                isTherePlus = true;
            }
            else
            {
                isThereMinus = true;
            }
            i++;
            while (i < diceCode.length())
            {
                x = diceCode.charAt(i);
                try
                {
                    addToLastNumber = Integer.parseInt(String.valueOf(x));
                }
                catch (NumberFormatException e)
                {
                    System.out.println("Nieprawidłowy format danych");
                    throw e;
                }
                lastNumber = lastNumber * 10 + addToLastNumber;
                i++;
            }

        }

        int rng = rn.nextInt(typeofDice) + 1;
        result = multiplier * rng;
        if (isTherePlus)
        {
            result += lastNumber;
        }
        if (isThereMinus)
        {
            result -= lastNumber;
        }
        return result;

    }



}
