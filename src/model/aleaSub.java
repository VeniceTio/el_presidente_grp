package model;


import controller.ElementControl;

import java.util.Map;
import java.util.Random;

public class aleaSub implements AbstractFormula {
    @java.lang.Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @java.lang.Override
    public void updateByLevers(Indicator indicator) {
        long oldValue = indicator.getValue();
        long nbStudent = ElementControl.getInstance().getElement("nombre d'étudiants").getValue();
        long maxVal = (long)(-0.01*nbStudent)+4500;
        long minVal = (long)(-0.01*nbStudent)+3800;
        if (nbStudent >200000){
            maxVal = 2500;
            minVal = 1800;
        }
        maxVal *= nbStudent;
        minVal *= nbStudent;

        long Val;
        Random rand = new Random();
        Val = minVal + rand.nextInt((int) (maxVal - minVal));
        Val = (oldValue+4*Val)/5;
        indicator.setValue(Val);
    }
}
