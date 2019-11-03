package Model;

import Control.ElementControl;

public class qualiteFormation implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        updateByLevers(indicator);
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        System.out.println("######################################################\n");
        float nbEleveProf = ElementControl.getInstance().getElement("nombre d'étudiant").getValue()/ElementControl.getInstance().getElement("nombre de professeur").getValue();
        float coef = 4+(1-((float)(ElementControl.getInstance().getElement("satisfaction professeur").getValue()/100.0)));
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
