package View;

import Control.ElementControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ProvisoryView extends PresidentView {

    private ElementControl _EC;

    public ProvisoryView(ElementControl EC) {
        super();
        this._EC = EC;

        JPanel groupeLevier = (JPanel) this.getContentPane();
        groupeLevier.setLayout(new GridLayout(10,1,10,10));

        IndicPanelDyn panelTitre = new IndicPanelDyn("argent_disponible",EC);
        groupeLevier.add(panelTitre);
        JPanel pan = new LevierPanel("fContractuel",EC);
        //pan.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan);

        JPanel pan1 = new LevierPanel("fTitulaire",EC);
        //pan1.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan1);

        JPanel pan2 = new LevierPanel("fPrimes",EC);
        //pan2.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan2);

        JPanel pan3 = new LevierPanel("rContractuel",EC);
        //pan3.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan3);

        JPanel pan4 = new LevierPanel("rTitulaire",EC);
        //pan4.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan4);

        JPanel pan5 = new LevierPanel("rPrimes",EC);
        //pan4.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan5);

        this.setVisible(true);
    }
}
