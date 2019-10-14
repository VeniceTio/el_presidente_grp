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
    public void MAJValue(){
        for (Map.Entry mapEntry : this._formule.entrySet()) {
            switch((String)mapEntry.getValue()){
                case "+":
                    this.setValue(this.getValue() + ((AbstractElement)mapEntry.getKey()).getValue());
                case "-":
                    this.setValue(this.getValue() - ((AbstractElement)mapEntry.getKey()).getValue());
                case "/":
                    this.setValue(this.getValue() / ((AbstractElement)mapEntry.getKey()).getValue());
                default:
                    this.setValue(this.getValue() + ((AbstractElement)mapEntry.getKey()).getValue());
            }
        }
    }
}
