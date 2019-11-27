package model;

import java.util.Map;

public class NombreProfesseur implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        long oldValue = lever.getOldValue();
        long IndicValue = indicator.getValue()-(oldValue/(Integer.parseInt(indicator.getFormula().get(lever))));
        System.out.println(IndicValue);
        indicator.setValue(IndicValue + (lever.getValue()/(Integer.parseInt(indicator.getFormula().get(lever)))));
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            System.out.println(mapEntry);
            System.out.println(indicator.getValue());
            indicator.setValue(indicator.getValue()+(((Lever)mapEntry.getKey()).getValue()/(Integer.parseInt((String)mapEntry.getValue()))));
        }
    }
}
