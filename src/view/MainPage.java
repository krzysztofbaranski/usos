package view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by krzysztof on 05/02/14.
 */
public class MainPage {
    private JPanel rootPane;

    public void setRightPane(JPanel rightPane) {
        this.rightPane = rightPane;
    }

    private JPanel leftPane;
    private JPanel rightPane;

    public JPanel getRightPane() {
        return rightPane;
    }

    public JPanel getRootPane() {
        return rootPane;
    }
}
