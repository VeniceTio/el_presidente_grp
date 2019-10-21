package View;

import Control.ElementControl;
import Model.LeverFamily;
import Model.Lever;

import javax.swing.*;
import java.awt.*;

public class GroupeLevier extends JPanel {
    private LeverFamily _FL;

    public GroupeLevier(LeverFamily FL, ElementControl EC){
        super();
        this._FL = FL;
        this.setLayout(new GridLayout(7,1,10,10));
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
