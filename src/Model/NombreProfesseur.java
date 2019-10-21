package Model;

import java.util.Map;

public class NombreProfesseur implements AbstractFormule{

    @Override
    public void majOneValue(Indicateur indicateur, Levier levier) {
        long oldValue = levier.getOldValue();
        long IndicValue = indicateur.getValue()-(oldValue/(Integer.parseInt(indicateur.getFormule().get(levier))));
        System.out.println(IndicValue);
        indicateur.setValue(IndicValue + (levier.getValue()/(Integer.parseInt(indicateur.getFormule().get(levier)))));
    }

    @Override
    public void majAllValue(Indicateur indicateur) {
        for (Map.Entry mapEntry : indicateur.getFormule().entrySet()) {
            System.out.println(mapEntry);
            System.out.println(indicateur.getValue());
            indicateur.setValue(indicateur.getValue()+(((Levier)mapEntry.getKey()).getValue()/(Integer.parseInt((String)mapEntry.getValue()))));
        }
    }
    public void clockforward(){}
}
