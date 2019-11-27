package view;

import controller.ElementControl;
import utils.ElementObserver;

import javax.swing.*;

public class IndicPanelDyn  extends JPanel implements ElementObserver {
        private JLabel _labelValue1;
        private ElementControl _EC;
        private String _name;

        public IndicPanelDyn(String name) {
            super();
            _EC = ElementControl.getInstance();
            _name = name;
            _labelValue1 = new JLabel(_EC.getElement(_name).toString());
            System.out.println(_EC.getElement(_name).toString());
            this.add(_labelValue1);
            _EC.getElement(name).add(this);
        }

        @Override
        public void update() {
            _labelValue1.setText(_EC.getElement(_name).toString());
        }
    }
