package model;

import controller.ElementControl;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Map;

public class Nombre implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }
    private static SecureRandom rand = new SecureRandom();
    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel le levier agit
     */
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
            long valuett = courbeInter1(value / 100);
            ArrayList<Long> history = indicator.get_history();
            int nbSemestre = Semestre.getInstance().getSemestre();
            int debut = 0;
            if (nbSemestre>2){
                debut = nbSemestre-2;
            }
            for (int i = debut;i<nbSemestre;i++){
                    valuett += history.get(i);
            }
            valuett /=3;
            indicator.setValue(valuett);
        }
        else{
            indicator.setValue(courbeInter2(value));
        }
    }

     /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
      * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbeInter1(long value){
        long nb_prof_rec = ElementControl.getInstance().getElement("rContractuel").getValue()/27000+ElementControl.getInstance().getElement("rTitulaire").getValue()/34000;
        long valCourbe;
        double min;
        double max;
        double ratio;

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

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbeInter2(long value){
        long valCourbe;
        double max;
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
