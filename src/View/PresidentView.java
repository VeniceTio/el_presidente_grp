package View;

import javax.swing.*;
import java.awt.*;

public abstract class PresidentView extends JFrame implements ElementObserver {
    public PresidentView() {


        //this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setSize(600, 480);
        this.setLayout(new BorderLayout());


    }
}
