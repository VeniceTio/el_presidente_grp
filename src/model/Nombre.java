package model;

import controller.ElementControl;

import java.util.Map;
import java.util.Random;

public class Nombre implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        int value = 0;
        int bit = 0;
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if (mapEntry.getValue() == "cI106") {
                value += ((AbstractElement) mapEntry.getKey()).getValue()*60;
                bit=1;
            }
            else if (mapEntry.getValue() == "cI104"){
                value += ((AbstractElement) mapEntry.getKey()).getValue()*40;
                bit=1;
            }
            else if (mapEntry.getValue() == "cI21"){
                value = (int)((AbstractElement) mapEntry.getKey()).getValue();
            }
        }
        if(bit==1) {
            indicator.setValue(courbeInter1(value / 100));
        }
        else{
            indicator.setValue(courbeInter2(value));
        }
    }

    public static long courbeInter1(long value){
        long nb_prof_rec = ElementControl.getInstance().getElement("rContractuel").getValue()/27000+ElementControl.getInstance().getElement("rTitulaire").getValue()/34000;
        long valCourbe;
        double min;
        double max;
        double ratio;
        Random rand = new Random();
        if (value <=0){
            valCourbe = 0;
        }
        else{
            if (value <= 10) {
                min = 0.004*value;
                max = 0.02 * value;
            } else if (value <= 50) {
                min = 0.004*value;
                max = 0.01*value +0.1;
            } else if (value <= 80) {
                min = 0.01*value-0.3;
                max = 0.013333*value + 0.066666;
            } else{
                min = 0.01*value-0.3;
                max = 0.025*value-1;
            }
            ratio = min + rand.nextDouble()*(max - min);
            valCourbe = (long)(ratio * nb_prof_rec);
        }
        return valCourbe;
    }
    public static long courbeInter2(long value){
        long valCourbe;
        double max;
        Random rand = new Random();
        if (value <=0){
            valCourbe = 0;
        }
        else{
            if (value <= 80) {
                max = 0.001625 * value +0.42;
            } else if (value <= 96) {
                max = 0.006875*value;
            } else if (value <= 100) {
                max = 0.2275*value -21.18;
            } else{
                max = 1.57;
            }
            valCourbe = (long)(rand.nextFloat()*max);
        }
        return valCourbe;
    }
}
