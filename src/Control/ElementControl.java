package Control;

import Model.AbstractElement;
import Model.FamilleLevier;
import Model.Indicateur;
import Model.Levier;
import View.GroupeLevier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ElementControl {
    private Collection<FamilleLevier> _groupes = new ArrayList<FamilleLevier>();
    private Map<String, AbstractElement> _MapElement = new HashMap<String, AbstractElement>();

    public ElementControl(){}
    public void addGroupe(FamilleLevier GL){
        this._groupes.add(GL);
    }
    public Collection<FamilleLevier> getGroupes() {
        return _groupes;
    }
    public AbstractElement get_Element(String name) {
        return _MapElement.get(name);
    }

    public Indicateur createIndicateur(String name, int value){
        Indicateur indic = new Indicateur(name,value);
        this._MapElement.put(name,indic);
        return indic;
    }
    public Levier createLevier(String name, int value){
        Levier levier = new Levier(name,value);
        this._MapElement.put(name,levier);
        return levier;
    }
}
