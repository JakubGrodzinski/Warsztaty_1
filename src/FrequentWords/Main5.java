package src.FrequentWords;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main5
{
    public static void main(String[] args)
    {
        String[] forb = new String[5];
        forb[0] = "oraz";
        forb[1] = "ponieważ";
        forb[2] = "albowiem";
        forb[3] = "jak";
        forb[4] = "nie";
        fWords();
        withoutForbidden(forb);
        ranking();

    }
    static void fWords ()
    {
        String url = "https://www.onet.pl/";
        Connection connect = Jsoup.connect(url);
        try
        {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links)
            {
                StringTokenizer st = new StringTokenizer(elem.text(), "!?\".,: ");
                while (st.hasMoreTokens())
                {
                    String tok = st.nextToken();
                    try
                    {
                        FileWriter fw = new FileWriter("popular_words.txt", true);
                        if (tok.length() > 2)
                        {
                            fw.append(tok + "\n");
                            fw.close();
                        }
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println("Błąd zapisu do pliku");
                    }
                }
            }
        }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
    }
    static void withoutForbidden (String[] forbWords)
    {
        File file = new File("popular_words.txt");
        boolean write = true;
        try
        {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine())
            {
                String tmp = scan.nextLine();
                for(int i = 0; i < forbWords.length; i++)
                {
                    if(tmp.equals(forbWords[i]))
                    {
                        write = false;
                    }
                }
                if(write)
                {
                    try
                    {
                        FileWriter out = new FileWriter("filtered_popular_words.txt", true);
                        out.append(tmp + "\n");
                        out.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("Błąd zapisu");
                    }
                }
                write = true;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Błąd odczytu");
        }
    }

    static int maxInArr (int[] arr)
    {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if(max < arr[i])
            {
                max = arr[i];
            }
        }
        return max;
    }

    static void ranking ()
    {
        int counterForTables = 0;
        int i = 0;
        String[] words;
        File file = new File("filtered_popular_words.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                counterForTables++;
                sc.nextLine();
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Błąd odczytu");
        }
            words = new String[counterForTables];
        try
        {
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine())
            {
                words[i] = scan.nextLine();
                i++;
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("Błąd odczytu");
        }
        int[] counterofInstances = new int[counterForTables];
        boolean countThisIndex = true;
        for(int j = 0; j < counterForTables; j++)
        {
            for(int k = 0; k < j; k++)
            {
                if(words[k].equals(words[j]) && counterofInstances[k] != 0)
                {
                    counterofInstances[k]++;
                    countThisIndex = false;
                    break;
                }
            }
            if(countThisIndex)
            {
                counterofInstances[j]++;
            }
            countThisIndex = true;
        }
        int max = maxInArr(counterofInstances);

            for(int k = max; k > 0; k--)
            {
                for(int l = 0; l < counterForTables; l++)
                {
                    if(counterofInstances[l] == k)
                    {
                        try
                        {
                            FileWriter op = new FileWriter("ranking.txt", true);
                            op.append(k + ": " + words[l] + "\n");
                            op.close();
                        }
                        catch (IOException e)
                        {
                            System.out.println("Błąd zapisu");
                        }
                    }
                }
            }
    }

}

