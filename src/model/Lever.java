package model;

import java.util.ArrayList;
import java.util.Collection;

public class Lever extends AbstractElement{
    /**
     * Liste contenant tous les indicateurs que le levier influence
     */
    private Collection<Indicator> _influencers = new ArrayList<Indicator>();

    /**
     * Méthode permettant de créer une instance de la classe Lever
     * @param name le nom du levier
     * @param value la valeur du levier
     */
    public Lever(String name, int value){
        super(name,value);
    }

    /**
     * Méthode permettant d'ajouter un indicateur à la liste des indicateurs
     * @param indic l'indicateur qu'on doit ajouter à la liste
     */
    public void addInfluencer(Indicator indic){
        _influencers.add(indic);
    }

    /**
     * Méthode permettant d'afficher le nom et la valeur du levier
     * @return la chaîne affichant le nom et la valeur
     */
    public String toString(){return get_name().substring(1) + " : "+ getValue();}

    /**
     * Méthode permettant de mettre à jour la valeur du levier
     * @param value la nouvelle valeur du levier
     */
    @Override
    public void setValue(long value){
        _oldValue = _value;
        _value = value;
        for (Indicator indic: _influencers) {
            indic.MajOneValue(this);
        }
        notifyObservers();
    }
}
