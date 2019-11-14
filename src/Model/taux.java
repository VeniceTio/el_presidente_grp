package Model;

import java.math.BigDecimal;
import java.util.Map;

public class taux implements AbstractFormula {

    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {

    }

    @Override
    public void updateByLevers(Indicator indicator) {
        indicator.setValue(0);
        long valMap;
        System.out.println("#####################################");
        System.out.println("nom indicateur : "+ indicator.get_name());
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            System.out.println("# # # # # # # # # # # # # # # # # # #");
            valMap = ((AbstractElement) mapEntry.getKey()).getValue();
            if (mapEntry.getValue() == "20c1") {
                indicator.setValue(indicator.getValue() + courbe1(valMap,0.2));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.2 courbe 1 val : "+ valMap + "\n val courbe : "+courbe1(valMap,0.2));
            }
            else if (mapEntry.getValue() == "40c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.4));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() + " coeff 0.4 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.4));
            }
            else if (mapEntry.getValue() == "40/100") {
                    indicator.setValue(indicator.getValue() + (long) (valMap * 0.4));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 40/100 val : "+ (valMap * 0.4)+" "+valMap);
                }
            else if (mapEntry.getValue() == "30/100") {
                indicator.setValue(indicator.getValue() + (long) (valMap * 0.3));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 30/100 val : "+ (valMap * 0.3)+" "+valMap);
                }
            else if (mapEntry.getValue() == "35c2") {
                indicator.setValue(indicator.getValue() + courbe2(valMap,0.35));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.35 courbe 2 val : "+ valMap + "\n val courbe : "+courbe2(valMap,0.35));
                }
            else if (mapEntry.getValue() == "35c3") {
                indicator.setValue(indicator.getValue() + courbe3(valMap,0.35));
                System.out.println(((AbstractElement) mapEntry.getKey()).get_name() +" coeff 0.35 courbe 3 val : "+ valMap + "\n val courbe : "+courbe3(valMap,0.35));
                }
            }

            if (indicator.getValue() < 0) {
                indicator.setValue(0);
            } else if (indicator.getValue() > 100) {
                indicator.setValue(100);
            }
        System.out.println("#####################################");
        }

    public static long courbe1(long value,double coef){
        long valCourbe;
        if (value <0){
            valCourbe = 0;
        }
        else if (value <=200000){
            valCourbe = (long)(((250/1000000)*value)*coef);
            valCourbe = new BigDecimal(250).divide(new BigDecimal(1000000)).multiply(new BigDecimal(value)).multiply(new BigDecimal(coef)).longValue();
        }
        else if (value <=300000){
            valCourbe = (long)((((475/1000000)*value)+45)*coef);
        }
        else if (value <= 500000){
            valCourbe = (long)((((12.5/1000000)*value)+93.75)*coef);
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        return valCourbe;
    }
    public static long courbe2(long value,double coef){
        long valCourbe;
        if (value <0){
            valCourbe = 0;
        }
        else if (value < 300000) {
            valCourbe = (long) (((66.6666 / 1000000) * value) * coef);
            System.out.println("1");
        } else if (value < 500000) {
            BigDecimal frac = new BigDecimal(300);
            frac = frac.divide(new BigDecimal(1000000));
            valCourbe = frac.multiply(new BigDecimal(value)).subtract(new BigDecimal(70)).multiply(new BigDecimal(coef)).longValue();
        } else if (value < 700000) {
            valCourbe = (long) ((((87.5 / 1000000) * value) + 36.25) * coef);
            System.out.println("3");
        } else if (value < 1000000) {
            valCourbe = (long) ((((8.33 / 1000000) * value) + 91.666) * coef);
            System.out.println("4");
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        return valCourbe;
    }
    public static long courbe3(long value,double coef){
        long valCourbe;
        if (value <0){
            valCourbe = 0;
        }
        else if (value <=100000){
            valCourbe = (long)(((100/1000000)*value)*coef);
        }
        else if (value <=400000){
            valCourbe = (long)((((296.66666/1000000)*value)-19.166)*coef);
        }
        else if (value <= 700000){
            valCourbe = (long)((((8.3333/1000000)*value)+94.16666)*coef);
        }
        else {
            valCourbe = (long)(coef * 100);
        }
        return valCourbe;
    }

}
