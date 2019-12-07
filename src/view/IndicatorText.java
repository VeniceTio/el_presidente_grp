package view;

import controller.ElementControl;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.ElementObserver;

public class IndicatorText implements ElementObserver {
    private Text _text;
    private ElementControl _EC;
    private String _name;

    public IndicatorText(String name) {
        super();
        _EC = ElementControl.getInstance();
        _name = name;
        _text = new Text(_EC.getElement(_name).toString());
        _text.setFont(new Font("Roboto", 17.0));
        _text.setFill(Color.WHITE);
        _EC.getElement(name).add(this);
    }

    public Text getText() {
        return this._text;
    }

    @Override
    public void update() {
        _text.setText(_EC.getElement(_name).toString());
    }
}
