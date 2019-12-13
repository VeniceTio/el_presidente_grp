package model;

import java.util.Map;

public class Rapport implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        updateByLevers(indicator);
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel les leviers agissent
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if(mapEntry.getValue()=="sur"){
                indicator.setValue(indicator.getValue()+((Indicator)mapEntry.getKey()).getValue());
            }
        }
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if (mapEntry.getValue()=="/"){
                indicator.setValue((long)((float)indicator.getValue()/(float)(((Indicator)mapEntry.getKey()).getValue())));
                System.out.println("# # # # # "+indicator.getValue());
            }
        }
    }
}
