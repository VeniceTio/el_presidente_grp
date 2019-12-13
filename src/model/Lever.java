package model;

import java.util.ArrayList;
import java.util.Collection;

public class Lever extends AbstractElement {
    /**
     * Liste contenant tous les indicateurs que le levier influence
     */
    private Collection<Indicator> _influencers = new ArrayList<Indicator>();
    /**
     * L'ordre de grandeur utilisé pour le contrôle du levier
     */
    private long _scale;

    /**
     * Constructeur de la classe Lever
     * @param name Le nom du levier
     * @param value La valeur du levier
     */
    public Lever(String name, int value,long scale){
        super(name,value);
        _scale = scale;
    }

    /**
     * Méthode renvoyant l'ordre de grandeur du levier
     * @return L'ordre de grandeur utilisé pour le contrôle du levier
     */
    public long getScale(){
        return _scale;
    }

    /**
     * Méthode permettant d'ajouter un indicateur à la liste des indicateurs
     * @param indic L'indicateur qu'on doit ajouter à la liste
     */
    public void addInfluencer(Indicator indic){
        _influencers.add(indic);
    }

    /**
     * Méthode permettant d'afficher le nom et la valeur du levier
     * @return Une chaîne de caractères contenant le nom et la valeur du levier
     */
    public String toString(){return get_name().substring(1) + " : "+ getValue();}

    /**
     * Méthode permettant de mettre à jour la valeur du levier
     * @param value La nouvelle valeur du levier
     */
    @Override
    public void setValue(long value){
        if (value<0){
            _oldValue = _value;
            _value = 0;
        }else {
            _oldValue = _value;
            _value = value;
        }

        // Mise à jour des indicateurs concernés
        for (Indicator indic: _influencers) {
            indic.MajOneValue(this);
        }
        notifyObservers();
    }
}
