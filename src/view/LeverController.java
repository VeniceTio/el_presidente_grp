package view;

import controller.ElementControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Lever;
import utils.ElementObserver;
import utils.Info;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class LeverController implements ElementObserver {
    /**
     * Différents éléments essentiels au fonctionnement du composant
     */
    Pane _pane;
    Text _textValue;
    String _name;
    ElementControl _ec;
    long _scale;


    /**
     * Constructeur de la classe LeverController
     * @param name Nom de du levier auquel le composant fait référence
     */
    public LeverController(String name) throws IOException {
        _name = name;
        _ec = ElementControl.getInstance();
        Parent cbase = FXMLLoader.load(getClass().getResource("../resources/fxml/lever_controller.fxml"));
        Pane croot = (Pane) cbase;;
        Text textValue = (Text)croot.getChildren().get(0); // Text[id=level-name]
        textValue.setText(name.substring(1).toUpperCase());
        _pane = croot;
        NumberFormat formatter = new DecimalFormat("##,###.##");
        _textValue = (Text)croot.getChildren().get(1); // Text[id=lever-value]
        _textValue.setText(formatter.format(_ec.getElement(_name).getValue()));
        _scale = ((Lever)(_ec.getElement(_name))).getScale();

        EventHandler<ActionEvent> buttonPlusHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _ec.getElement(name).setValue(_ec.getElement(name).getValue() + _scale);
                event.consume();
            }
        };
        EventHandler<ActionEvent> buttonMinusHandler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                _ec.getElement(name).setValue(_ec.getElement(name).getValue() - _scale);
                event.consume();
            }
        };

        Button btnPlus = (Button)croot.getChildren().get(2); // Button[id=lever-btn-plus]
        btnPlus.setOnAction(buttonPlusHandler);
        Button btnMinus = (Button)croot.getChildren().get(3); // Button[id=lever-btn-minus]
        btnMinus.setOnAction(buttonMinusHandler);

        Info infoLevier = new Info();
        ImageView info = (ImageView)croot.getChildren().get(4); // ImageView lctrl-info
        Tooltip.install(info, new InfoTooltip(infoLevier.getLeverInfo(_name)));
        _ec.getElement(name).add(this);
    }

    /**
     * Méthode renvoyant le Pane du composant
     * @return Pane du composant
     */
    public Pane getPane() {
        return _pane;
    }

    /**
     * Méthode permettant de mettre à jour la valeur du composant lorsque l'indicateur change
     */
    @Override
    public void update() {
        NumberFormat formatter = new DecimalFormat("##,###.##");
        _textValue.setText(formatter.format(_ec.getElement(_name).getValue()));
        // _labelLastValue.setText("valeur précedente : "+String.valueOf(_EC.getElement(_name).getLastValue()));
    }
}
