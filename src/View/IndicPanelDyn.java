package View;

import Control.ElementControl;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class IndicPanelDyn  extends JPanel implements ElementObserver{
        private JLabel _labelValue;
        private ElementControl _EC;
        private String _name;

        public IndicPanelDyn(String name,ElementControl EC) {
            super();
            _EC = EC;
            _name = name;
            _labelValue = new JLabel(name + " : " +  EC.get_Element(name).getValue());
            //this.add(new JLabel(name));

            this.add(_labelValue);
            EC.get_Element(name).add(this);
        }

        @Override
        public void update(int value) {
            _labelValue.setText(_name + " : "+ _EC.get_Element(_name).getValue());
        }
    }
