package view;

import controller.ElementControl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import model.Indicator;
import model.IndicatorType;
import utils.ElementObserver;
import utils.Info;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class IndicatorText implements ElementObserver {
    /**
     * Différents éléments essentiels au fonctionnement du composant
     */
    private ElementControl _ec;
    private String _name;
    private Pane _pane;
    private Text _textValue;

    /**
     * Constructeur de la classe IndicatorText
     * @param name Nom de l'indicateur auquel le composant fait référence
     */
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
        } else if(_name.equals("argent disponible")) {
            txt = formatter.format(Integer.valueOf(_ec.getElement(_name).toString().replaceAll("[^\\d-]", ""))) + " €";
            _textValue.setStyle("-fx-fill: #000;");
        }
        _textValue.setText(txt);

        Info infoIndicator = new Info();
        ImageView info = (ImageView)lroot.getChildren().get(2); // ImageView indtxt-info
        Tooltip.install(info, new InfoTooltip(infoIndicator.getIndicatorInfo(_name)));

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
        String txt = formatter.format(_ec.getElement(_name).getValue());

        Indicator ind = (Indicator) _ec.getElement(_name);
        if(ind.getType() == IndicatorType.PERCENTAGE) {
            txt = txt + " %";
        } else if(_name.equals("argent disponible")) {
            txt = formatter.format(Long.valueOf(_ec.getElement(_name).toString().replaceAll("[^\\d-]", ""))) + " €";
        }
        _textValue.setText(txt);
    }
}
