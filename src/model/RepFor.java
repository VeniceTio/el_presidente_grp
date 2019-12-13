package model;

import controller.ElementControl;

import java.util.ArrayList;

public class RepFor implements  AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) { }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel les leviers agissent
     */
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

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */
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
