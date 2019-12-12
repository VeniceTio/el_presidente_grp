package model;

import controller.ElementControl;

public class Neutre implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        float val_bat = (float)(ElementControl.getInstance().getElement("valorisation batiment").getValue());
        float val_bien = (float)(ElementControl.getInstance().getElement("valorisation bien").getValue());
        indicator.setValue((long)((val_bat/val_bien)*100));
        System.out.println("\n######################################################");
        System.out.println("valeur bat    : " + val_bat);
        System.out.println("valeur bien   : " + val_bien);
        System.out.println("pourcent  : " + val_bat/val_bien);
        System.out.println("######################################################");
    }
}
