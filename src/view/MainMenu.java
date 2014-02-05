package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by krzysztof on 05/02/14.
 */
public class MainMenu {
    private JButton stronaGłównaButton;
    private JButton wyszukajButton;
    private JButton mojeKontoButton;
    private JButton przedmiotyButton;
    private JButton ustawieniaButton;
    private JButton wylogujButton;
    private JPanel menu;

    public MainMenu() { wyszukajButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SearchPanel panel = new SearchPanel();
            panel.getSearchPanelRoot().setVisible(true);
        }
    });}
}
