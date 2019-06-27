

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.Element;
import javax.swing.text.html.parser.Parser;

public class ButtonEventListener extends JFrame implements ActionListener  {
    public static void actionPerformed(ActionEvent e) {

        //String message;
        for(
                Element masre : ParsFootball.run().matches ) {
            System.out.println(masre);
            JOptionPane.showMessageDialog(null,masre,"Вилки", JOptionPane.PLAIN_MESSAGE);
        }

    }



}
