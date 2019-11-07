package Model;

import Control.ElementControl;

public class neutre implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @Override
    public void updateByLevers(Indicator indicator) {
        float val_bat = (float)(ElementControl.getInstance().getElement("valorisation batiment").getValue());
        float val_bien = (float)(ElementControl.getInstance().getElement("valorisation bien").getValue());
        indicator.setValue((long)((val_bat/val_bien)*100));
        System.out.println("\n######################################################");
        System.out.println("valeur bat    : " + val_bat);
        System.out.println("valeur bien   : " + val_bien);
        System.out.println("pourcent  : " + val_bien/val_bat);
        System.out.println("######################################################");
    }
}
