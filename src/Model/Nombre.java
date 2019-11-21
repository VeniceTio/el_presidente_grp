package Model;

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
        int nbValue = 0;
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if (mapEntry.getValue() == "cI106") {
                value += ((AbstractElement) mapEntry.getKey()).getValue()*60;
            }
            else if (mapEntry.getValue() == "cI104"){
                value += ((AbstractElement) mapEntry.getKey()).getValue()*40;
            }
        }
        indicator.setValue(courbeInter1(value/100));
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
    /**public static long courbeInter2(long value){
        return ;
    }**/
}
