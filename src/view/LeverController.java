package view;

import controller.ElementControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import utils.ElementObserver;

import java.io.IOException;

public class LeverController implements ElementObserver {
    Pane _pane;
    Text _textValue;
    String _name;
    ElementControl _ec;

    public LeverController(String name) throws IOException {
        _name = name;
        _ec = ElementControl.getInstance();
        Parent cbase = FXMLLoader.load(getClass().getResource("../resources/fxml/lever_controller.fxml"));
        Pane croot = (Pane) cbase;;
        Text textValue = (Text)croot.getChildren().get(0); // Text[id=level-name]
        textValue.setText(name);
        _pane = croot;
        _textValue = (Text)croot.getChildren().get(1); // Text[id=lever-value]
        _textValue.setText(String.valueOf(_ec.getElement(_name).getValue()));

        EventHandler<ActionEvent> buttonPlusHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _ec.getElement(name).setValue(_ec.getElement(name).getValue() + 50000);
                event.consume();
            }
        };
        EventHandler<ActionEvent> buttonMinusHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _ec.getElement(name).setValue(_ec.getElement(name).getValue() - 50000);
                event.consume();
            }
        };

        Button btnPlus = (Button)croot.getChildren().get(2); // Button[id=lever-btn-plus]
        btnPlus.setOnAction(buttonPlusHandler);
        Button btnMinus = (Button)croot.getChildren().get(3); // Button[id=lever-btn-minus]
        btnMinus.setOnAction(buttonMinusHandler);

        // System.out.println(croot.getChildren());

        _ec.getElement(name).add(this);
    }


    public Pane getPane() {
        return _pane;
    }

    @Override
    public void update() {
        _textValue.setText(String.valueOf(_ec.getElement(_name).getValue()));
        // _labelLastValue.setText("valeur précedente : "+String.valueOf(_EC.getElement(_name).getLastValue()));
    }
}
