package view;

import app.*;
import app.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by krzysztof on 05/02/14.
 */
public class LoginPage {
    private JPanel loginPageRoot;
    private JFormattedTextField _mail;
    private JPasswordField _passwd;
    private JButton loginButton;

    public LoginPage() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vector<Vector<Object>> v = null;
                try {
                    v = controler.Utility.getData("select decode(md5('" +
                            _passwd.getText() +
                            "'), 'hex') = (select passwd from passwords where person_id = (select id from persons where mail = '" +
                            _mail.getText() + "')),(select id from persons where mail = '" + _mail.getText() + "')");
                    //System.out.println(v);
                    if(v.elementAt(0).elementAt(0) == true) {
                        User.person_id = (Long) v.elementAt(0).elementAt(1);
                        Window.mainFrame.setContentPane(new MainPage().getRootPane());
                        Window.mainFrame.setTitle("USOS - Main Page");
                        Window.mainFrame.repaint();
                        Window.mainFrame.setVisible(true);
                    } else {
                        // permission denied
                    }
                } catch(Throwable exc) {
                    System.out.println(v);
                    exc.printStackTrace();
                    _mail.setText("");
                    _passwd.setText("");
                }
            }
        });
    }

    public JPanel getLoginPageRoot() {
        return loginPageRoot;
    }
}
