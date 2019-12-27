package model;

import controller.ElementControl;

import java.util.Map;

public class PourcentSatisfaction implements AbstractFormula {

    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator L'indicateur sur lequel agit le levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator L'indicateur sur lequel le levier agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        System.out.println("################## voila regarde quoi #################");
        System.out.println("#    "+indicator.toString());
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            System.out.println("###################");
            System.out.println("#    "+mapEntry);
            if (mapEntry.getValue()=="r"){
                long val = ElementControl.getInstance().getElement("nombre d'étudiants").getValue();
                indicator.setValue(indicator.getValue() + (int)(courbe1(((AbstractElement)mapEntry.getKey()).getValue()/val)*0.3333));
                System.out.println("# courbe 1 : "+ (courbe1(((AbstractElement)mapEntry.getKey()).getValue()/val)*0.3333));
            } else if(mapEntry.getValue() =="3"){
                indicator.setValue(indicator.getValue() + (long)(((AbstractElement)mapEntry.getKey()).getValue()*0.3333));
                System.out.println("# courbe 1 : "+(((AbstractElement)mapEntry.getKey()).getValue()*0.3333));
            } else if(mapEntry.getValue() =="c34"){
                indicator.setValue(indicator.getValue() + (long)(courbe3(((AbstractElement)mapEntry.getKey()).getValue())*0.4));
                System.out.println("# courbe 1 : "+(long)(courbe3(((AbstractElement)mapEntry.getKey()).getValue())*0.5));
            } else if(mapEntry.getValue() =="c33"){
                indicator.setValue(indicator.getValue() + (long)(courbe3(((AbstractElement)mapEntry.getKey()).getValue())*0.333));
                System.out.println("# courbe 1 : "+(courbe3(((AbstractElement)mapEntry.getKey()).getValue())*0.333));
            } else if (mapEntry.getValue() =="c26"){
                long rapport = ((AbstractElement) mapEntry.getKey()).getValue();
                //System.out.println("##########");
                //System.out.println("#   "+rapport+"   #");
                //System.out.println("##########");
                indicator.setValue(indicator.getValue() + (long)(courbe2(rapport)*0.6));
                System.out.println("# courbe 1 : "+(courbe2(rapport)*0.5));
            }
        }
        System.out.println("#    "+indicator.toString());
        System.out.println("################## c'est fini quoi #################");
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
        if (indicator.getValue()<0){
            indicator.setValue(0);
        }
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * A=(0,0),B=(100,10),C=(200,40),D=(300,70)
     * E=(600,100)
     * @param value Valeur X pour laquelle on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static int courbe1(long value){
        int valCourbe=0;
        if (value<0){
            valCourbe = -10;
        } else if (value <=100){
            valCourbe = (int)(0.1*value);
        } else if (value <=300){
            valCourbe = (int)(0.3*value-20);
        } else if (value <= 600){
        valCourbe = (int)(0.1*value+40);
        } else {
            valCourbe = 100;
        }
        if(valCourbe>100){
            valCourbe = 100;
        }
        return valCourbe;
    }
    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * A=(0,100),B=(10,80),C=(20,70),D=(30,40),E=(60,-80)
     * @param value Valeur X pour laquelle on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */
    public static int courbe2(long value){
        int valCourbe=0;
        if (value<0){
            valCourbe = 100;
        } else if (value <=10){
            valCourbe = (int)(-2*value+100);
        } else if (value <=20){
            valCourbe = (int)(-value+90);
        } else if (value <= 30){
            valCourbe = (int)(-3*value+130);
        } else if (value <= 60){
            valCourbe = (int)(-4*value+160);
        } else {
            valCourbe = -80;
        }
        if(valCourbe>100){
            valCourbe = 100;
        }
        return valCourbe;
    }

    /**
     * Méthode renvoyant la valeur Y sur une courbe pour un X donné
     * A=(0,-30),B=(40,10),C=(50,40),D=(80,90),E=(100,100)
     * @param value Valeur X pour laquelle on cherche la valeur Y sur la courbe
     * @return Valeur Y correspondant au X donné sur la courbe
     */

    public static int courbe3(long value){
        int valCourbe=0;
        if (value<0){
            valCourbe = -30;
        } else if (value <=40){
            valCourbe = (int)(0.6*value-20);
        } else if (value <=50){
            valCourbe = (int)(3*value-110);
        } else if (value <= 80){
            valCourbe = (int)(1.6666*value-43.3333);
        } else if (value <= 100){
            valCourbe = (int)(0.5*value+50);
        } else {
            valCourbe = 100;
        }
        if(valCourbe>100){
            valCourbe = 100;
        }
        return valCourbe;
    }
}
