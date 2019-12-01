package model;

import controller.ElementControl;

import java.math.BigDecimal;

public class NombreEleve implements AbstractFormula {
    /**
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
     * @param indicator l'indicateur sur lequelle agit le levier
     * @param lever le levier qui agit sur l'indicateur
     */
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir de ses leviers
     * @param indicator l'indicateur sur lequelles les leviers agit
     */
    @Override
    public void updateByLevers(Indicator indicator) {
        BigDecimal value = new BigDecimal(1000000).multiply(new BigDecimal(ElementControl.getInstance().getElement("réputation de formation").getValue()/100.0));
        System.out.println("---#--# 1 000 000 * "+ElementControl.getInstance().getElement("réputation de formation").getValue()/100.0+" = "+value+" #--#---");
        value = value.multiply(new BigDecimal(courbe1(ElementControl.getInstance().getElement("fFrais d'inscription").getValue())));
        System.out.println("---#--# "+value+" #--#---");
        indicator.setValue(value.longValue());
    }
    public static double courbe1(long value){
        double valCourbe;
        if (value <=0){
            valCourbe = 2;
        }
        else if (value <=400){
            valCourbe = -0.0025*value+2;
        }
        else if (value <=1200){
            valCourbe = -0.000625*value + 1.25;
        }
        else if (value <= 5000){
            valCourbe = -0.0001184211*value+0.642105;
        }
        else if (value <= 10000){
            valCourbe = -0.0000094*value + 0.097;
        }
        else if (value <= 20000){
            valCourbe = -0.00000027*value+0.0057;
        }
        else if (value <= 30000){
            valCourbe = -0.000000027*value+0.00084;
        }
        else if (value <= 40000){
            valCourbe = -0.000000002*value+0.00009;
        }
        else if (value <= 50000){
            valCourbe = -0.0000000001*value +0.000014;
        }
        else if (value <= 60000){
            valCourbe = -0.0000000006*value+0.000039;
        }
        else {
            valCourbe = 0.000002;
        }
        return valCourbe;
    }
}
