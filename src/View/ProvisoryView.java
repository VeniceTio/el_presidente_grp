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
        this.add(new JLabel("Argent disponible : "+String.valueOf(EC.get_Element("argent_disponible").getValue())));
        JPanel pan = this.createJPanel("fContractuel");

        this.getContentPane().add(pan);
        this.setVisible(true);
    }

    public JPanel createJPanel(String name){
        JPanel panel = new JPanel();

        panel.add(new JLabel(name));
        ElementControl EC = this._EC;
        panel.add( new JButton( new AbstractAction("moins") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element("name").getValue()-10000);
            }
        }));

        panel.add(new JLabel(String.valueOf(EC.get_Element(name).getValue())));

        panel.add( new JButton( new AbstractAction("plus") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element("name").getValue()+10000);
            }
        }));
        return panel;
    }

    @Override
    public void update() {

    }
}
