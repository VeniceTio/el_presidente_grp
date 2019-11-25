package Model;

import java.util.Map;
import java.util.Random;

public class RepRec implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        long com = 0;
        long val1= 0;
        long val2= 0;
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if (mapEntry.getValue() == "com") {
                com = courbe1(((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue() == "val1") {
                val1 = courbe2(((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue() == "val2") {
                val2 = courbe3(((AbstractElement) mapEntry.getKey()).getValue());
            }
        }
        long val = ((com*val1)/100)*val2;
        if (val>100){
            val=100;
        }
        indicator.setValue(val);
    }
    public static long courbe1(long value){
        double max = 0;
        if (value <=0){
            max = 0;
        }
        else{
            if (value <= 500000) {
                max = 0.0001 * value;
            } else if (value <= 1000000) {
                max = 0.00004*value+30;
            } else if (value <= 1400000) {
                max = 0.000025*value +45;
            } else if (value <= 1800000) {
                max = 0.00001333*value +61.333;
            } else if (value <= 3000000) {
                max = 0.000006*value +76;
            } else{
                max = 100;
            }
        }
        return (long)max;
    }
    public static long courbe2(long value){
        double max;
        if (value <=0){
            max = 0;
        } else{
            if (value <= 25000) {
                max = 0.002 * value;
            } else if (value <= 50000) {
                max = 0.0016*value+10;
            } else if (value <= 100000) {
                max = 0.0002*value +80;
            } else{
                max = 100;
            }
        }
        return (long)max;
    }
    public static long courbe3(long value){
        double max;
        if (value ==0){
            max = 1;
        } else if (value ==1){
            max = 1.2;
        } else {
            max = 1.4;
        }
        return (long)max;
    }
}
