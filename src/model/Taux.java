package model;

import controller.ElementControl;

import java.math.BigDecimal;
import java.util.Map;

public class Taux implements AbstractFormula {

    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) { }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel le levier agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        long valMap;
        //System.out.println("#####################################");
        //System.out.println("nom indicateur : "+ indicator.get_name());
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            //System.out.println("# # # # # # # # # # # # # # # # # # #");
            valMap = ((AbstractElement) mapEntry.getKey()).getValue();
            if (mapEntry.getValue() == "20c1") {
                indicator.setValue(indicator.getValue() + courbe1(valMap,0.2));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.2 courbe 1 val : "+ valMap + "\n val courbe : "+courbe1(valMap,0.2));
            } else if (mapEntry.getValue() == "20c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.2));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.2 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.2));
            } else if (mapEntry.getValue() == "30c6") {
                indicator.setValue(indicator.getValue() + courbe6(valMap,0.3));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.3 courbe 6 val : "+ valMap + "\n val courbe : "+courbe6(valMap,0.3));
            }
            else if (mapEntry.getValue() == "40c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.4));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() + " coeff 0.4 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.4));
            } else if (mapEntry.getValue() == "30c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.3));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() + " coeff 0.3 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.4));
            }
            else if (mapEntry.getValue() == "35c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.35));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.35 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.35));
                }
            else if (mapEntry.getValue() == "35c3") {
                indicator.setValue(indicator.getValue() + courbe3(valMap,0.35));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.35 courbe 3 val : "+ valMap + "\n val courbe : "+courbe3(valMap,0.35));
                }
            else if (mapEntry.getValue() == "18c4") {
                long nb_prof_rec = ElementControl.getInstance().getElement("rContractuel").getValue()/27000+ElementControl.getInstance().getElement("rTitulaire").getValue()/34000;
                indicator.setValue(indicator.getValue() + courbe4(valMap/nb_prof_rec,0.18));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.18 courbe 4 val : "+ valMap/nb_prof_rec + "\n val courbe : "+courbe4(valMap/nb_prof_rec,0.18));
            }
            else if (mapEntry.getValue() == "27c4") {
                long nb_prof_rec = ElementControl.getInstance().getElement("rContractuel").getValue()/27000+ElementControl.getInstance().getElement("rTitulaire").getValue()/34000;
                indicator.setValue(indicator.getValue() + courbe4(valMap/nb_prof_rec,0.27));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.27 courbe 4 val : "+ valMap/nb_prof_rec + "\n val courbe : "+courbe4(valMap/nb_prof_rec,0.27));
            }
            else if (mapEntry.getValue() == "30c4") {
                long nb_prof_rec = ElementControl.getInstance().getElement("rContractuel").getValue()/27000+ElementControl.getInstance().getElement("rTitulaire").getValue()/34000;
                indicator.setValue(indicator.getValue() + courbe4(valMap/nb_prof_rec,0.3));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.18 courbe 3 val : "+ valMap/nb_prof_rec + "\n val courbe : "+courbe4(valMap/nb_prof_rec,0.3));
            }
            else if (mapEntry.getValue() == "40/100") {
                long value =(long) (valMap);
                indicator.setValue(indicator.getValue() + courbe5(value,0.4));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 40/100 val : "+ courbe5(value,0.4)+" av "+valMap);
            }
            else if (mapEntry.getValue() == "30/100") {
                long value =valMap;
                indicator.setValue(indicator.getValue() + courbe5(value,0.3));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 30/100 val : "+ courbe5(value,0.3)+" "+valMap);
            }
            else if (mapEntry.getValue() == "25/100") {
                long value =valMap;
                indicator.setValue(indicator.getValue() + courbe5(value,0.25));
                //System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 25/100 val : "+ courbe5(value,0.25)+" "+valMap);
            }
            }

            if (indicator.get_name()=="taux de réussite"){
                indicator.setValue((long)(indicator.getValue()*0.4));
            }
            if (indicator.getValue() < 0) {
                indicator.setValue(0);
            } else if (indicator.getValue() > 100) {
                indicator.setValue(100);
            }
            //System.out.println(indicator.toString());
        //System.out.println("#####################################");
        }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe1(long value,double coef){
        long valCourbe;
        if (value <=1){
            valCourbe = -30;
        }
        else if (value <=200000){
            //50valCourbe = (long)(((250/1000000)*value)*coef);
            valCourbe = new BigDecimal(250).divide(new BigDecimal(1000000)).multiply(new BigDecimal(value)).multiply(new BigDecimal(coef)).longValue();
        }
        else if (value <=300000){
            valCourbe = (long)((((0.475/1000)*value)-45)*coef);
        }
        else if (value <= 500000){
            valCourbe = (long)((((12.5/1000000)*value)+93.75)*coef);
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe2(long value,double coef){
        long valCourbe;
        if (value <=1){
            valCourbe = -30;
        }
        else if (value <= 300000) {
            valCourbe = (long) (((66.6666 / 1000000) * value));
            //System.out.println("1");
        } else if (value <= 500000) {
            BigDecimal frac = new BigDecimal(300);
            frac = frac.divide(new BigDecimal(1000000));
            valCourbe = frac.multiply(new BigDecimal(value)).subtract(new BigDecimal(70)).longValue();
        } else if (value <= 700000) {
            valCourbe = (long) ((((87.5 / 1000000) * value) + 36.25));
            //System.out.println("3");
        } else if (value <= 1000000) {
            valCourbe = Math.round((((8.33 / 1000000) * value) + 91.666) *100)/100;
            //System.out.println("4");
        }
        else {
            valCourbe = (long)(100);
        }
        valCourbe = (long)(courbeMalus(valCourbe)*coef);
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe3(long value,double coef){
        long valCourbe;
        if (value <=1){
            valCourbe = -30;
        }
        else if (value <=100000){
            valCourbe = (long)((0.0001*value));
        }
        else if (value <=400000){
            valCourbe = (long)((((2.9666666/10000)*value)-19.166));
        }
        else if (value <= 700000){
            valCourbe = Math.round((((8.3333/1000000)*value)+94.16666)*1000)/1000;
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        valCourbe = (long)(courbeMalus(valCourbe)*coef);
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * A=(0,-30),B=(4 000, 2.5),C=(15 000,40)
     * D=(25 000, 70),E=(30 000,90),F=(42 000,100)
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe4(long value,double coef){
        long valCourbe;
        //System.out.println(nb_prof_rec +" " +value);
        if (value <=1){
            valCourbe = -30;
        } else if (value <=4000){
            valCourbe = Math.round((((0.75/100)*value)-30)*coef);
        }
        else if (value <=25000){
            valCourbe = Math.round((((3.3333/1000)*value)-13.3333)*coef);
        }
        else if (value <= 30000){
            valCourbe = Math.round((((5.0/1000)*value)-55)*coef);
        }
        else if (value <= 42000){
            valCourbe = Math.round((((0.41666/1000)*value)+82.5)*coef);
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe5(long value, double coef){
        long valCourbe;
        double opcoef = 2-coef;
        valCourbe = (long)(value * coef);
/*
        if (value<=30){
            valCourbe = (long)(opcoef*value-opcoef*30);
        }
        else if (value<=95){
            valCourbe=(long)(value*coef);
        }
        else {
            valCourbe=(long)(-2*value+(305*coef));
        }
        if (valCourbe>100){
            valCourbe = 100;
        }*/
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * A=(0,-70),B=(50,50),C=(100,100)
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @param coef Coefficient
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbe6(long value, double coef){
        long valCourbe;
        if(value<0){
            valCourbe=(long)(-70*coef);
        }
        if (value<=50){
            valCourbe = (long)((2.4*value-80)*coef);
        }
        else if (value<=100){
            valCourbe=(long)((value)*coef);
        }
        else {
            valCourbe=(long)(100*coef);
        }
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * @param value Valeur X dont on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static long courbeMalus(long value){
        long valCourbe;
        if(value<2){
            valCourbe=(long)(-50);
        }
        if (value<=50){
            valCourbe = (long)(2*value-50);
        }
        else if (value<=100){
            valCourbe=(long)(value);
        }
        else {
            valCourbe=100;
        }
        return valCourbe;
    }
}
