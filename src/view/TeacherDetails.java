package view;

import app.User;
import controler.Utility;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.util.Vector;

/**
 * Created by Użytkownik on 07.02.14.
 */
public class TeacherDetails {
    private JTextPane _name;
    private JPanel root;

    public TeacherDetails(long id) {
        Vector<Vector<Object>> v = Utility.getData("select fname, lname from persons where id = " + id);
        _name.setText(v.get(0).get(0) + " " + v.get(0).get(1));

    }
    public JPanel getRoot() {

        return root;
    }
}
