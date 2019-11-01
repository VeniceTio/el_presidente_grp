package Model;

import java.util.Map;

public class Argent implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    public void updateByOneLever(Indicator indicator, Lever lever){
        if(indicator.get_name()=="argent disponible") {
            long oldValue = lever.getOldValue();
            long IndicValue;
            //System.out.println(oldValue);
            if (indicator.getFormula().get(lever) == "+") {
                IndicValue = indicator.getValue() - oldValue;
                System.out.println(IndicValue);
                indicator.setValue(IndicValue + lever.getValue());
            } else if (indicator.getFormula().get(lever) == "-") {
                IndicValue = indicator.getValue() + oldValue;
                indicator.setValue(IndicValue - lever.getValue());
            }
        }
        else {
            updateByLevers(indicator);
        }
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    public void updateByLevers(Indicator indicator){
        //indicateur.setValue(1);
        if(indicator.get_name()!="argent disponible") {
            indicator.setValue(1);}
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            System.out.println(mapEntry);
            if ( mapEntry.getValue() =="+") {
                indicator.setValue(indicator.getValue() + ((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue()=="-"){
                indicator.setValue(indicator.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
            }
            else if(mapEntry.getValue() =="*"){
                indicator.setValue(indicator.getValue() * ((AbstractElement)mapEntry.getKey()).getValue());
            }
        }
    }
}
