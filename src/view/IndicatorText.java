package view;

import controller.ElementControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.Indicator;
import model.IndicatorType;
import utils.ElementObserver;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IndicatorText implements ElementObserver {
    private ElementControl _ec;
    private String _name;
    private Pane _pane;
    private Text _textValue;

    public IndicatorText(String name) throws Exception {
        _name = name;
        _ec = ElementControl.getInstance();
        Parent cbase = FXMLLoader.load(getClass().getResource("../resources/fxml/indicator_text.fxml"));
        Pane lroot = (Pane) cbase;;
        Text textName = (Text)lroot.getChildren().get(0); // Text[id=indtxt-name]
        textName.setText(name.toUpperCase());
        _pane = lroot;
        NumberFormat formatter = new DecimalFormat("##,###.##");
        _textValue = (Text)lroot.getChildren().get(1); // Text[id=indtxt-value]
        String txt = formatter.format(_ec.getElement(_name).getValue());

        Indicator ind = (Indicator) _ec.getElement(_name);
        if(ind.getType() == IndicatorType.PERCENTAGE) {
            txt = txt + " %";
        } else if(_name == "argent disponible") {
            txt = formatter.format(Integer.valueOf(_ec.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €";
            _textValue.setStyle("-fx-fill: #000;");
        }
        _textValue.setText(txt);
        _ec.getElement(name).add(this);
    }

    public Pane getPane() {
        return _pane;
    }

    @Override
    public void update() {
        NumberFormat formatter = new DecimalFormat("##,###.##");
        String txt = formatter.format(_ec.getElement(_name).getValue());

        Indicator ind = (Indicator) _ec.getElement(_name);
        if(ind.getType() == IndicatorType.PERCENTAGE) {
            txt = txt + " %";
        } else if(_name == "argent disponible") {
            txt = formatter.format(Integer.valueOf(_ec.getElement(_name).toString().replaceAll("[^\\d.]", ""))) + " €";
        }
        _textValue.setText(txt);
    }
}
