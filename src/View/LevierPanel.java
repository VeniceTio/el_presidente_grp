package View;

import Control.ElementControl;
import Uttilities.ElementObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LevierPanel extends JPanel implements ElementObserver {
    private JTextArea _labelValue;
    private JLabel _labelLastValue;
    private ElementControl _EC;
    private String _name;

    public LevierPanel(String name,ElementControl EC) {
        super();
        _EC = EC;
        _name = name;
        Dimension DimButton = new Dimension(100, 30);
        _labelValue = new JTextArea(String.valueOf(EC.getElement(name).getValue()));
        _labelValue.setPreferredSize(DimButton);
        //_labelValue.setHorizontalAlignment(SwingConstants.CENTER);
        _labelLastValue = new JLabel("valeur précédente : " + String.valueOf(EC.getElement(name).getLastValue()));

        JLabel labelName = new JLabel(name);
        labelName.setPreferredSize(new Dimension(200,30));
        labelName.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.DARK_GRAY));

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(labelName);
        this.add(_labelLastValue);

        JButton JBMoins = new JButton(new AbstractAction("moins") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.getElement(name).setValue(EC.getElement(name).getValue() - 10000);
            }
        });
        JBMoins.setPreferredSize(DimButton);
        this.add(JBMoins);

        this.add(_labelValue);

        JButton JBPlus = new JButton(new AbstractAction("plus") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.getElement(name).setValue(EC.getElement(name).getValue() + 10000);
            }
        });
        JBPlus.setPreferredSize(DimButton);
        this.add(JBPlus);
        EC.getElement(name).add(this);
    }

    @Override
    public void update() {
        _labelValue.setText(String.valueOf(_EC.getElement(_name).getValue()));
        _labelLastValue.setText("valeur précedente : "+String.valueOf(_EC.getElement(_name).getLastValue()));
    }
}
