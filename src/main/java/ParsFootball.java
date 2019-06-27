import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ParsFootball extends Thread  {

    //public static Document parsMatches() extends Thread{
        @Override
        public void run()   {
            try {

               String url = "https://www.oddsfan.ru/live_odds/soccer";


                //String url = "file:///C:/Users/Олег/Desktop/Лайв%20коэффициенты_%20букмекерские%20коэффициенты%20на%20Футбол%20в%20режиме%20лайв.html";
              Document  page = Jsoup.parse(new URL(url), 30000);

                Element table = page.select("div[class=tab-content]").first();
                Elements matches = table.select("tr[class=live]");

               /*
                Element ssilka = matches.select("td[class=event-holder]").first();
                Element urlMatch = ssilka.select("a[href]").first();


                System.out.println(urlMatch.text());

                String urlMatches = "urlMatch.text()";

                Document pageLiveMatchHockey = Jsoup.parse(new URL(urlMatches), 30000);

                System.out.println(pageLiveMatchHockey);
*/



                for (Element matc : matches) {
                    System.out.println(matc.text());
                }

            } catch (MalformedURLException e) {
                System.out.println("Что-то со ссылкой " + e);
            }
            catch (IOException e) {}






                /*Element table = page.select("div[class=tab-content]").first();
                Elements matches = table.select("tr[class=live]");

                for (Element matc : matches) {
                    System.out.println(matc.text());
                }*/


        }

    }


