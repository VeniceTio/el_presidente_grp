package view;

import controller.ElementControl;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import utils.ElementObserver;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IndicatorText implements ElementObserver {
    private Text _text;
    private ElementControl _EC;
    private String _name;

    public IndicatorText(String name) {
        super();
        _EC = ElementControl.getInstance();
        _name = name;

        if(_name == "argent disponible") {
            NumberFormat formatter = new DecimalFormat("##,###.##");
            _text = new Text(formatter.format(Integer.valueOf(_EC.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €");
            _text.setFont(new Font("Roboto Bold", 32.0));
            _text.setFill(Color.BLACK);
        } else {
            _text = new Text(_EC.getElement(_name).toString());
            _text.setFont(new Font("Roboto", 17.0));
            _text.setFill(Color.WHITE);
        }
        _EC.getElement(name).add(this);
    }

    public Text getText() {
        return this._text;
    }

    @Override
    public void update() {

        if(_name == "argent disponible") {
            NumberFormat formatter = new DecimalFormat("##,###.##");
            _text.setText(formatter.format(Integer.valueOf(_EC.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €");
        } else {
            _text.setText(_EC.getElement(_name).toString());
        }
    }
}
