package view;

import javax.swing.*;

public abstract class PresidentView extends JFrame{
    public PresidentView() {

        super("El Presidente !");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(1920, 1080);
        this.setLocationRelativeTo(null);


    }
}
