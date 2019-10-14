package Control;

import Model.AbstractElement;
import Model.Indicateur;
import Model.Levier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ElementControl {
    private Collection<AbstractElement> _Elements;
    private Map<String, AbstractElement> _MapElement = new HashMap<String, AbstractElement>();

    public ElementControl(){
        this._Elements = new ArrayList<AbstractElement>();
    }
    public void add(AbstractElement AE){
        this._Elements.add(AE);
    }
    public Collection<AbstractElement> get_Elements() {
        return _Elements;
    }
    public AbstractElement get_Element(String name) {
        return _MapElement.get(name);
    }

    public Indicateur createIndicateur(String name, int value){
        Indicateur indic = new Indicateur(name,value);
        this._Elements.add(indic);
        this._MapElement.put(name,indic);
        return indic;
    }
    public Levier createLevier(String name, int value){
        Levier levier = new Levier(name,value);
        this._Elements.add(levier);
        this._MapElement.put(name,levier);
        return levier;
    }
}
