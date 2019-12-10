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
        long maxVal = 4000 * ElementControl.getInstance().getElement("nombre d'étudiant").getValue();
        long minVal = 3400 * ElementControl.getInstance().getElement("nombre d'étudiant").getValue();
        long Val;
        Random rand = new Random();
        Val = minVal + rand.nextInt((int) (maxVal - minVal));
        Val = (oldValue+4*Val)/5;
        indicator.setValue(Val);
    }
}
