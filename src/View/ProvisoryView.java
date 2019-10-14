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

        JPanel pan = new LevierPanel("fContractuel",EC);
        this.getContentPane().add(pan);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
