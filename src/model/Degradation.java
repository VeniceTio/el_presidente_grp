package model;

import controller.ElementControl;

public class Degradation implements AbstractFormula {
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
        long val = ElementControl.getInstance().getElement("valorisation batiment").getValue();
        long ent = ElementControl.getInstance().getElement("iEntretien").getValue();
        long ajout = 0;
        long ajout1 = 0;
        long ajoutR = 0;
        float pourcent = ((float)(ent)/(float)(val))*2;
        if (pourcent<0.1){
            ajout = (long)(-5000000*pourcent+1000000);
        }
        else if (pourcent<0.2){
            ajout = (long)(-4000000*pourcent+900000);
        }
        else if (pourcent<0.3){
            ajout = (long)(-800000*pourcent+250000);
        }
        else {
            ajout = 10000;
        }
        if (ElementControl.getInstance().getElement("iConstruction").getValue()>0){
            ajout1 = ElementControl.getInstance().getElement("iConstruction").getValue();
        }
        if (ElementControl.getInstance().getElement("iRenovation").getValue()>0){
            ajoutR += ElementControl.getInstance().getElement("iRenovation").getValue();
        }
        indicator.setValue(indicator.getValue()+ajout1+ajoutR-(ajout*6));
        ElementControl.getInstance().getElement("valorisation bien").setValue(ElementControl.getInstance().getElement("valorisation bien").getValue()+ajout);
        if(indicator.getValue()>ElementControl.getInstance().getElement("valorisation bien").getValue()){
            ElementControl.getInstance().getElement("valorisation bien").setValue(indicator.getValue());
        }


        System.out.println("\n######################################################");
        System.out.println("valeur    : " + val);
        System.out.println("entretien : " + ent);
        System.out.println("pourcent  : " + pourcent);
        System.out.println("ajout     : " + ajout);
        System.out.println("######################################################");
    }
}
