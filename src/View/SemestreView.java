package View;

import Control.ElementControl;
import Model.Semestre;
import Uttilities.ElementObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SemestreView extends JPanel implements ElementObserver {
    private JLabel _labelValue1;

    public SemestreView() {
        super();
        _labelValue1 = new JLabel(Semestre.getInstance().toString());
        this.add(_labelValue1);
        Semestre.getInstance().add(this);
        JButton ButtonClock = new JButton( new AbstractAction("Next Semester") {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ElementControl.getInstance().ClockForvard();
            }
        });
        this.add(ButtonClock);
    }

    @Override
    public void update() {
        _labelValue1.setText(Semestre.getInstance().toString());
    }
}
