package Model;

import java.util.Map;

public class pourcentSatisfaction implements AbstractFormula {
    public long getPoint(long value, String chaine){
        long nbPoint = 0;
        if ( chaine =="s") {
            if (value>0){
                if (value>100000){
                    if(value>500000){
                        if(value>1000000){
                            if(value>10000000){
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
        else if (chaine=="r"){
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
    @Override
    public void updateByOneLever(Indicator indicator, Lever lever) {
        if(indicator.getFormula().get(lever) =="3"){
            indicator.setValue(indicator.getValue() + ((lever.getOldValue()/3)-(lever.getValue()/3)));
        }
        else{
            long oldValue = getPoint(lever.getOldValue(),indicator.getFormula().get(lever));
            long IndicValue = getPoint(lever.getValue(),indicator.getFormula().get(lever));;
            indicator.setValue(indicator.getValue()+(oldValue-IndicValue));
        }
        //System.out.println(oldValue);
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
    }

    @Override
    public void updateByLevers(Indicator indicator) {
        for (Map.Entry mapEntry : indicator.getFormula().entrySet()) {
            //System.out.println(mapEntry);
            if ( mapEntry.getValue() =="s") {
                if (((AbstractElement) mapEntry.getKey()).getValue()>0){
                    if (((AbstractElement) mapEntry.getKey()).getValue()>100000){
                        if(((AbstractElement) mapEntry.getKey()).getValue()>500000){
                            if(((AbstractElement) mapEntry.getKey()).getValue()>1000000){
                                if(((AbstractElement) mapEntry.getKey()).getValue()>10000000){
                                    indicator.setValue(indicator.getValue() + 32);
                                }
                                else {
                                    indicator.setValue(indicator.getValue() + 16);
                                }
                            }
                            else {
                                indicator.setValue(indicator.getValue() + 8);
                            }
                        }
                        else {
                            indicator.setValue(indicator.getValue() + 4);
                        }
                    }
                    else {
                        indicator.setValue(indicator.getValue() + 2);
                    }
                }
            }
            else if (mapEntry.getValue()=="r"){
                if (((AbstractElement) mapEntry.getKey()).getValue()>0){
                    if (((AbstractElement) mapEntry.getKey()).getValue()>10000){
                        if(((AbstractElement) mapEntry.getKey()).getValue()>50000){
                            if(((AbstractElement) mapEntry.getKey()).getValue()>100000){
                                if(((AbstractElement) mapEntry.getKey()).getValue()>1000000){
                                    indicator.setValue(indicator.getValue() + 32);
                                }
                                else {
                                    indicator.setValue(indicator.getValue() + 16);
                                }
                            }
                            else {
                                indicator.setValue(indicator.getValue() + 8);
                            }
                        }
                        else {
                            indicator.setValue(indicator.getValue() + 4);
                        }
                    }
                    else {
                        indicator.setValue(indicator.getValue() + 2);
                    }
                }
            }
            else if(mapEntry.getValue() =="3"){
                indicator.setValue(indicator.getValue() + (((AbstractElement)mapEntry.getKey()).getValue()/3));
            }
        }
        if (indicator.getValue()>100){
            indicator.setValue(100);
        }
    }
}
