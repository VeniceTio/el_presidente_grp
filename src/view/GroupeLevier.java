package view;

import controller.ElementControl;
import model.LeverFamily;
import model.Lever;

import javax.swing.*;
import java.awt.*;

public class GroupeLevier extends JPanel {
    private LeverFamily _FL;

    public GroupeLevier(LeverFamily FL, ElementControl EC){
        super();
        this._FL = FL;
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.GREEN));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new JLabel(FL.getName()));
        for (Lever lever : FL.getLevers()
             ) {
            this.add(new LevierPanel(lever.get_name(),EC));
        }

        this.setVisible(true);
    }

    public String getName(){
        return _FL.getName();
    }

}
