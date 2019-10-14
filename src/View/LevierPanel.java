package View;

import Control.ElementControl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LevierPanel extends JPanel implements ElementObserver{
    private JLabel _labelValue;
    private ElementControl _EC;
    private String _name;

    public LevierPanel(String name,ElementControl EC) {
        super();
        _EC = EC;
        _name = name;
        _labelValue = new JLabel(String.valueOf(EC.get_Element(name).getValue()));
        this.add(new JLabel(name));

        this.add(new JButton(new AbstractAction("moins") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element(name).getValue() - 10000);
            }
        }));

        this.add(_labelValue);

        this.add(new JButton(new AbstractAction("plus") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                EC.get_Element(name).setValue(EC.get_Element(name).getValue() + 10000);
            }
        }));
        EC.get_Element(name).add(this);
    }

    @Override
    public void update(int value) {
        _labelValue.setText(String.valueOf(_EC.get_Element(_name).getValue()));
    }
}
