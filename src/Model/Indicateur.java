package Model;

import java.util.HashMap;
import java.util.Map;

public class Indicateur extends AbstractElement{
    private Map<AbstractElement, String> _formule = new HashMap<AbstractElement, String>();
    private AbstractFormule _MAJ;
    private Boolean _statique;

    public Indicateur(String name,int value,Boolean status){
        super(name,value);
        _statique = status;
    }
    public Indicateur(String name,int value,AbstractFormule maj,Boolean status){
        super(name,value);
        _MAJ = maj;
        _statique = status;
    }

    public void addFacteur(AbstractElement AB, String action){
        this._formule.put(AB,action);
    }
    public Map<AbstractElement, String> getFormule(){return _formule;}
    public void setMAJ(AbstractFormule maj){_MAJ = maj;}
    public void MajOneValue(Levier levier){
        _MAJ.majOneValue(this,levier);
    }
    public void initValue(){
        _MAJ.majAllValue(this);
    }
    public String toString(){return this.get_name() + " : "+ this.getValue();}

    public void ClockForvard(){
        if (_statique){
            initValue();
        }

    }
}
