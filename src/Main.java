import app.Window;
import view.LoginPage;
import view.MainPage;

import javax.swing.*;

/**
 * Created by krzysztof on 05/02/14.
 */
public class Main {
    public static void main(String[] args) {
        Window.mainFrame = new JFrame("LoginPage");
        Window.mainFrame.setContentPane(new LoginPage().getLoginPageRoot());
        Window.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Window.mainFrame.pack();
        Window.mainFrame.setSize(600,400);
        Window.mainFrame.setVisible(true);
    }
}
