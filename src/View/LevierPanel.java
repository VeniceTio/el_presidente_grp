package View;

import Control.ElementControl;
import Uttilities.ElementObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LevierPanel extends JPanel implements ElementObserver {
    private JLabel _labelValue;
    private ElementControl _EC;
    private String _name;

    public LevierPanel(String name,ElementControl EC) {
        super();
        _EC = EC;
        _name = name;
        Dimension DimButton = new Dimension(100, 30);
        _labelValue = new JLabel(String.valueOf(EC.get_Element(name).getValue()));
        _labelValue.setPreferredSize(DimButton);
        _labelValue.setHorizontalAlignment(SwingConstants.CENTER);

        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
        this.add(new JLabel(name));

        JButton JBMoins = new JButton(new AbstractAction("moins") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element(name).getValue() - 10000);
            }
        });
        JBMoins.setPreferredSize(DimButton);
        this.add(JBMoins);

        this.add(_labelValue);

        JButton JBPlus = new JButton(new AbstractAction("plus") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element(name).getValue() + 10000);
            }
        });
        JBPlus.setPreferredSize(DimButton);
        this.add(JBPlus);
        EC.get_Element(name).add(this);
    }

    @Override
    public void update(int value) {
        _labelValue.setText(String.valueOf(_EC.get_Element(_name).getValue()));
    }
}
