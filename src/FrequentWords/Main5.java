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
        String[] forb = new String[2];
        forb[0] = "FIFA";
        forb[1] = "Trump";
        fWords();
        withoutForbidden(forb);

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

}

