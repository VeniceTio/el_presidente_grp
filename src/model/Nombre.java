package model;

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
        long valCourbe;
        long min;
        long max;
        Random rand = new Random();
        if (value <=0){
            valCourbe = 0;
        }
        else{
            if (value <= 10) {
                min = 250 * value;
                max = 875 * value;
            } else if (value <= 50) {
                min = (int)(187.5*value) + 625;
                max = (int)(531.25*value) + 3437;
            } else if (value <= 80) {
                min = (int)(333.33*value + 6666.66);
                max = (int)(333.33*value + 13333.3);
            } else{
                min = (int)(436*value -14880);
                max = (int)(500*value);
            }
            valCourbe = min + rand.nextInt((int) (max - min));
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
