import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Parser extends JFrame {
    private JButton button = new JButton("Запустить");


    public Parser() {
        super("SilverForks");
        this.setBounds(100,100,250,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,2,2,2 ));

//        container.add(button);
        button.addActionListener(ParsFootball);
        container.add(button);
    }

    public static Document parsHockey() throws IOException {

        String url = "https://www.oddsfan.ru/popular/hockey";
//        String url = "file:///C:/Users/Олег/Desktop/Хоккей_%20букмекерские%20коэффициенты%20на%20популярные%20матчи.html";

        Document pageHockey = Jsoup.parse(new URL(url), 30000);

        return pageHockey;

    }
         public static void koefMatches() throws  IOException {
            Document page = parsHockey();
            Element table = page.select("div[class=tab-content]").first();
            //Document page = parsHockey();
            // System.out.println(table);

            Elements matches = table.select("tr[class]");
            for (Element matc : matches) {
                System.out.println(matc.text());
            }

        }

    }
