package Model;

import java.util.HashMap;
import java.util.Map;

public class Indicateur extends AbstractElement{
    private Map<AbstractElement, String> _formule = new HashMap<AbstractElement, String>();

    public Indicateur(String name,int value){
        super(name,value);
    }

    public void addFacteur(AbstractElement AB, String action){
        this._formule.put(AB,action);
    }
    public void MajOneValue(Levier levier){
        int oldValue = levier.getOldValue();
        int IndicValue;
        System.out.println(oldValue);
        if ( (String)_formule.get(levier)=="+") {
            IndicValue = this.getValue()-oldValue;
            System.out.println(IndicValue);
            this.setValue(IndicValue + levier.getValue());
        }
        else if ((String)_formule.get(levier)=="-"){
            IndicValue = this.getValue()+oldValue;
            this.setValue(IndicValue - levier.getValue());
        }
    }
    public void MAJALLValue(){
        for (Map.Entry mapEntry : this._formule.entrySet()) {
            System.out.println(mapEntry);
                if ( (String)mapEntry.getValue()=="+") {
                    this.setValue(this.getValue() + ((AbstractElement) mapEntry.getKey()).getValue());
                }
                else if ((String)mapEntry.getValue()=="-"){
                    this.setValue(this.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
                }
        }
    }
}
