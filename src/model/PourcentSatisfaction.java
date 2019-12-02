package model;

import java.util.Map;

public class PourcentSatisfaction implements AbstractFormula {
    /**
     * Méthode permettant de renvoyer le nombre de point en fonction de la grandeur de la valeurç
     * @param value la valeur qu'on doit traiter
     * @param chaine l'option de traitement
     * @return
     */
    public long getPoint(long value, String chaine){
        long nbPoint = 0;
        if (chaine=="r"){
            if (value>0){
                if (value>10000){
                    if(value>50000){
                        if(value>100000){
                            if(value>1000000){
                                nbPoint = 32;
                            }
                            else {
                                nbPoint = 16;
                            }
                        }
                        else {
                            nbPoint = 8;
                        }
                    }
                    else {
                        nbPoint = 4;
                    }
                }
                else {
                    nbPoint = 2;
                }
            }
        }
        return nbPoint;
    }

    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        /*long dif;
        if(indicator.getFormula().get(lever) =="3"){
            if (lever.getOldValue() < lever.getValue()) {
                dif = ((lever.getValue() / 3) - (lever.getOldValue() / 3));
                indicator.setValue(indicator.getValue() + dif);
            }
            else {
                dif = (lever.getOldValue() / 3) - (lever.getValue() / 3);
                indicator.setValue(indicator.getValue() - dif);
            }
        }
        else{

            long oldValue = getPoint(lever.getOldValue(),indicator.getFormula().get(lever));
            long IndicValue = getPoint(lever.getValue(),indicator.getFormula().get(lever));
            if (oldValue < IndicValue) {
                indicator.setValue(indicator.getValue()+(IndicValue-oldValue));
            }
            else{
                indicator.setValue(indicator.getValue()-(oldValue-IndicValue));
            }
        }
        //System.out.println(oldValue);
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }*/
    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            //System.out.println(mapEntry);
            if (mapEntry.getValue()=="r"){                                        //à modifier par des courbes
                indicator.setValue(indicator.getValue() + (int)(courbe1(((AbstractElement)mapEntry.getKey()).getValue())*0.3333));
            }
            else if(mapEntry.getValue() =="3"){
                indicator.setValue(indicator.getValue() + (long)(((AbstractElement)mapEntry.getKey()).getValue()*0.3333));

            }
            else if(mapEntry.getValue() =="50/100"){
                indicator.setValue(indicator.getValue() + (long)(((AbstractElement)mapEntry.getKey()).getValue()*0.5));

            }
            else if (mapEntry.getValue() =="25"){
                long rapport = ((AbstractElement) mapEntry.getKey()).getValue();
                System.out.println("##########");
                System.out.println("#   "+rapport+"   #");
                System.out.println("##########");
                indicator.setValue(indicator.getValue() + (long)(courbe2(rapport)*0.5));
            }
        }
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
        if (indicator.getValue()<0){
            indicator.setValue(0);
        }
    }

    /**
     * A=(0,-50),B=(30 000,10),C=(50 000,25),D=(100 000,50)
     * E=(1 000 000,98),F=(15 000 000,100)
     * @param value
     * @return
     */
    public static int courbe1(long value){
        int valCourbe=0;
        if (value<0){
            valCourbe = -50;
        } else if (value <=30000){
            valCourbe = (int)(0.002*value-50);
        } else if (value <=50000){
            valCourbe = (int)(0.00075*value-12.5);
        } else if (value <= 100000){
            valCourbe = (int)(0.0005*value);
        } else if (value <= 1000000){
        valCourbe = (int)(0.0000533*value+44.6666);
        } else if (value <= 15000000){
            valCourbe = (int)(0.000000142857*value+97.8571428);
        } else {
            valCourbe = 100;
        }
        if(valCourbe>100){
            valCourbe = 100;
        }
        return valCourbe;
    }
    /**
     * A=(0,100),B=(10,80),C=(20,70),D=(30,40),E=(60,-80)
     * @param value
     * @return
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
}
