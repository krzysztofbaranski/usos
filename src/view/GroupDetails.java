package view;

import controler.Learning;
import controler.Utility;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

/**
 * Created by Użytkownik on 07.02.14.
 */
public class GroupDetails {
    private JPanel root;
    private JTextPane mainInfoTextPane;
    private JTextPane titleTextPane;
    private JTextPane przedmiotTextPane;
    Vector<Vector<Object>> v;

   public GroupDetails(long groupId) {
       v = Utility.getData(Learning.getGroupSQL(groupId));
       titleTextPane.setText("" + v.get(0).get(1));
       przedmiotTextPane.setText("" + v.get(0).get(6));
       przedmiotTextPane.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               app.Window.mainFrame.setContentPane(new CourseDetails((long)v.get(0).get(5)).getRoot());
               app.Window.mainFrame.setVisible(true);
           }

       });
       mainInfoTextPane.setText(v.get(0).get(2)+"\nRok "+v.get(0).get(3)+"/"+((Integer)v.get(0).get(3)+1)+", semestr "+v.get(0).get(4)+"\nLiczba godzin: "+v.get(0).get(7));
   }

    public JPanel getRoot() {

        return root;
    }

}
