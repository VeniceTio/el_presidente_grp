package view;

import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class InfoTooltip extends Tooltip {
    public InfoTooltip(String text) {
        super(text);
        this.setFont(new Font("Roboto", 14));
        this.setShowDelay(new Duration(0));
    }
}
