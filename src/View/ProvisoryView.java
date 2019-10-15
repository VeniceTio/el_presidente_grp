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
        this.setTitle("El Presidente");
        IndicPanelDyn panelTitre = new IndicPanelDyn("argent_disponible",EC);
        setContentPane(panelTitre);

        Box groupeLevier = Box.createVerticalBox();

        groupeLevier.setLayout(new BoxLayout(groupeLevier,BoxLayout.Y_AXIS));
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

        JPanel pan4 = new LevierPanel("rContractuel",EC);
        //pan4.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupeLevier.add(pan4);

        this.getContentPane().add(groupeLevier);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
