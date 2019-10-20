package Model;

import java.util.Map;

public class Argent implements AbstractFormule{
    public void majOneValue(Indicateur indicateur,Levier levier){
        int oldValue = levier.getOldValue();
        int IndicValue;
        //System.out.println(oldValue);
        if ( (String)indicateur.getFormule().get(levier)=="+") {
            IndicValue = indicateur.getValue()-oldValue;
            System.out.println(IndicValue);
            indicateur.setValue(IndicValue + levier.getValue());
        }
        else if ((String)indicateur.getFormule().get(levier)=="-"){
            IndicValue = indicateur.getValue()+oldValue;
            indicateur.setValue(IndicValue - levier.getValue());
        }
    }
    public void majAllValue(Indicateur indicateur){
        for (Map.Entry mapEntry : indicateur.getFormule().entrySet()) {
            System.out.println(mapEntry);
            if ( (String)mapEntry.getValue()=="+") {
                indicateur.setValue(indicateur.getValue() + ((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if ((String)mapEntry.getValue()=="-"){
                indicateur.setValue(indicateur.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
            }
        }
    }
}
