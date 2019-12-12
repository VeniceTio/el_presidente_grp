package model;

import controller.ElementControl;

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
        indicator.setValue((long)newValue);
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
