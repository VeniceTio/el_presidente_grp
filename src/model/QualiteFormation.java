package model;

import controller.ElementControl;

public class QualiteFormation implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        updateByLevers(indicator);
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        System.out.println("######################################################\n");
        float nbEleveProf = ElementControl.getInstance().getElement("nombre d'étudiants").getValue()/ElementControl.getInstance().getElement("nombre de professeurs").getValue();
        float coef = 4+(1-((float)(ElementControl.getInstance().getElement("satisfaction professeurs").getValue()/100.0)));
        float soust = (nbEleveProf*coef)-100;
        if (soust<0){
            soust=0;
        }
        if (soust>100){
            soust=100;
        }
        indicator.setValue(100-(long)(soust));
        System.out.println("nbEleveProf : " + nbEleveProf);
        System.out.println("coef : " + coef);
        System.out.println("soust : " + soust);
        System.out.println("######################################################");
    }
}
