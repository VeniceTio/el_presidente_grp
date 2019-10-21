package Model;

import java.util.Map;

public class Argent implements AbstractFormule{
    public void majOneValue(Indicateur indicateur,Levier levier){
        if(indicateur.get_name()=="argent disponible") {
            long oldValue = levier.getOldValue();
            long IndicValue;
            //System.out.println(oldValue);
            if ((String) indicateur.getFormule().get(levier) == "+") {
                IndicValue = indicateur.getValue() - oldValue;
                System.out.println(IndicValue);
                indicateur.setValue(IndicValue + levier.getValue());
            } else if ((String) indicateur.getFormule().get(levier) == "-") {
                IndicValue = indicateur.getValue() + oldValue;
                indicateur.setValue(IndicValue - levier.getValue());
            }
        }
        else {
            majAllValue(indicateur);
        }
    }
    public void majAllValue(Indicateur indicateur){
        //indicateur.setValue(1);
        if(indicateur.get_name()!="argent disponible") {indicateur.setValue(1);}
        for (Map.Entry mapEntry : indicateur.getFormule().entrySet()) {
            System.out.println(mapEntry);
            if ( (String)mapEntry.getValue()=="+") {
                indicateur.setValue(indicateur.getValue() + ((AbstractElement) mapEntry.getKey()).getValue());
            }
            else if ((String)mapEntry.getValue()=="-"){
                indicateur.setValue(indicateur.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
            }
            else if ( (String)mapEntry.getValue()=="+2"){
                if (Semestre.getInstance().getSemestre()%2==0){
                    indicateur.setValue(indicateur.getValue() + ((AbstractElement) mapEntry.getKey()).getValue());
                }
            }
            else if ( (String)mapEntry.getValue()=="-2"){
                if (Semestre.getInstance().getSemestre()%2==0){
                    indicateur.setValue(indicateur.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
                }
            }
            else if((String)mapEntry.getValue()=="*"){
                indicateur.setValue(indicateur.getValue() * ((AbstractElement)mapEntry.getKey()).getValue());
            }
        }
    }
}
