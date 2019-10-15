package View;

import Control.ElementControl;
import Model.FamilleLevier;
import Model.Levier;

import javax.swing.*;
import java.awt.*;

public class GroupeLevier extends JPanel {
    private FamilleLevier _FL;

    public GroupeLevier(FamilleLevier FL, ElementControl EC){
        super();
        this._FL = FL;
        this.setLayout(new GridLayout(10,1,10,10));
        for (Levier levier: FL.getContenus()
             ) {
            this.add(new LevierPanel(levier.get_name(),EC));
        }
        this.setVisible(true);
    }

    public String getName(){
        return _FL.getName();
    }

}
