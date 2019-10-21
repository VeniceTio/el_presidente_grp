package View;

import Control.ElementControl;
import Uttilities.ElementObserver;

import javax.swing.*;

public class IndicPanelDyn  extends JPanel implements ElementObserver {
        private JLabel _labelValue1;
        private ElementControl _EC;
        private String _name;

        public IndicPanelDyn(String name,ElementControl EC) {
            super();
            _EC = EC;
            _name = name;
            _labelValue1 = new JLabel(_EC.get_Element(_name).toString());
            System.out.println(_EC.get_Element(_name).toString());
            this.add(_labelValue1);
            EC.get_Element(name).add(this);
        }

        @Override
        public void update(long value) {
            _labelValue1.setText(_EC.get_Element(_name).toString());
        }
    }
