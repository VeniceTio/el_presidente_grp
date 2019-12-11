package model;

import java.util.ArrayList;
import java.util.Collection;

public class Lever extends AbstractElement{
    /**
     * Liste contenant tous les indicateurs que le levier influence
     */
    private Collection<Indicator> _influencers = new ArrayList<Indicator>();
    /**
     * Attribut contenant le niveau d'augmentation
     */
    private long _scale;
    /**
     * Méthode permettant de créer une instance de la classe Lever
     * @param name le nom du levier
     * @param value la valeur du levier
     */
    public Lever(String name, int value,long scale){
        super(name,value);
        _scale = scale;
    }
    /**
     * Méthode permettant de renvoyer l'ancienne valeur de l'AbstractElement
     * @return l'ancienne valeur de l'AbstractElement
     */
    public long getScale(){
        return _scale;
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
        if (value<0){
            _oldValue = _value;
            _value = 0;
        }else {
            _oldValue = _value;
            _value = value;
        }

        //maj des indicateurs influencé
        for (Indicator indic: _influencers) {
            indic.MajOneValue(this);
        }
        notifyObservers();
    }
}
