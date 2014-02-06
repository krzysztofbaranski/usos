package view;

import app.User;

import javax.swing.*;

/**
 * Created by krzysztof on 06/02/14.
 */
public class AccountPanel {
    private JPanel menu;
    private JLabel _name;
    private JLabel _status;
    private JTextField _address;
    private JTextField _mail;
    private JTextField _phone;
    private JPanel root;
    private JButton button1;
    private JButton button2;
    private JButton button3;

    public AccountPanel() {
        _name.setText(User.fName + " " + User.lName);
        _status.setText(User.status);
        _address.setText(User.address);
        _mail.setText(User.mail);
        _phone.setText(User.phone);
    }

    public JPanel getRoot() {

        return root;
    }
}
