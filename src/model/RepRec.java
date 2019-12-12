package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class RepRec implements AbstractFormula {
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        long com = 0;
        long val1= 0;
        double val2= 0;
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            if (mapEntry.getValue() == "com") {
                com = courbe1(((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue() == "val1") {
                val1 = courbe2(((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if (mapEntry.getValue() == "val2") {
                val2 = courbe3(((AbstractElement) mapEntry.getKey()));
            }
        }
        long val = (long)(((com*val1)/100)*val2);
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

    /**
     * courbe regissant le coefficient multiplicateur d'un prix nobel
     * @param element
     * @return
     */
    public static double courbe3(AbstractElement element){
        long value = element.getValue();
        int nbSemestre = Semestre.getInstance().getSemestre();
        int debut = 0;
        double max = 0;
        ArrayList<Long> history = (ArrayList<Long>)(element.get_history());
        if (nbSemestre>7){
            debut = nbSemestre-7;
        }
        for (int i = debut;i<nbSemestre;i++){
            if(history.get(i)==1){
                value++;
            }
        }
        max = 0.2*value+1;
        return max;
    }
}
