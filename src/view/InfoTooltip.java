package view;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class InfoTooltip extends Tooltip {

    /**
     * Constructeur de la classe InfoTooltip
     * @param text Texte du tooltip
     */
    public InfoTooltip(String text) {
        super(text);
        this.setFont(new Font("Roboto", 15));
        this.setShowDelay(new Duration(0));
        this.setStyle("-fx-background-color: #FFF; -fx-text-fill: #000;");
    }
}
