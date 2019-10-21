package Control;

import Model.*;
import View.GroupeLevier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ElementControl implements Time{

    private Collection<FamilleLevier> _groupes = new ArrayList<FamilleLevier>();
    private Map<String, AbstractElement> _MapElement = new HashMap<String, AbstractElement>();
    private ArrayList<Indicateur> _indicateurs = new ArrayList<Indicateur>();

    private static ElementControl Instance = null;

    private ElementControl(){}
    public static ElementControl getInstance(){
        if(Instance == null){
            Instance = new ElementControl();
        }
        return Instance;
    }
    public void addGroupe(FamilleLevier GL){
        this._groupes.add(GL);
    }
    public Collection<FamilleLevier> getGroupes() {
        return _groupes;
    }
    public AbstractElement get_Element(String name) {
        return _MapElement.get(name);
    }


    public Indicateur createIndicateur(String name, int value, AbstractFormule AF,Boolean statique){
        Indicateur indic = new Indicateur(name,value,AF,statique);
        this._MapElement.put(name,indic);
        this._indicateurs.add(indic);
        return indic;
    }
    public Levier createLevier(String name, int value){
        Levier levier = new Levier(name,value);
        this._MapElement.put(name,levier);
        return levier;
    }

    @Override
    public void ClockForvard() {
        for(Indicateur indic : _indicateurs){
            indic.ClockForvard();
        }
        Semestre.getInstance().ClockForvard();
    }
}
