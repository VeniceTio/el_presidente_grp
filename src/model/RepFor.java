package model;

import controller.ElementControl;

import java.util.ArrayList;

public class RepFor implements  AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @Override
    public void updateByLevers(Indicator indicator) {
        ElementControl ec = ElementControl.getInstance();
        long TR = ec.getElement("taux de réussite").getValue();
        double Com = ec.getElement("cCommunication").getValue();
        double DotSpe = ec.getElement("fDotation Specifique").getValue();
        long SE = ec.getElement("satisfaction étudiante").getValue();
        double newValue = (TR*2+SE)/2.0;
        Com = courbe1(Com/3);
        DotSpe = courbe1(DotSpe*2);
        newValue *= ((Com*0.01) * (DotSpe*0.01));

        //mise dans le temps
        long valuett = (long)newValue;
        ArrayList<Long> history = indicator.get_history();
        int nbSemestre = Semestre.getInstance().getSemestre();
        int debut = 1;
        if (nbSemestre>4){
            debut = nbSemestre-4;
            for (int i = debut;i<nbSemestre;i++){
                valuett += history.get(i);
            }
            valuett /=5;
        }
        indicator.setValue(valuett);
    }

    public static double courbe1(double value){
        double newValue = value/10000.0;
        if (newValue>100){
            newValue=100;
        }else if (newValue<0){
            newValue=0;
        }
        return newValue;
    }
}
