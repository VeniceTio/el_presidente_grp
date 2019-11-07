package Model;

import Uttilities.Observable;

import java.util.ArrayList;
import java.util.Collection;

public class AbstractElement extends Observable implements Time{
    /**
     * nom du levier ou de l'indicateur
     */
    private String _name;
    /**
     * valeur actuelle de l'indicateur ou du levier
     */
    long _value;
    /**
     * ancienne valeur de l'indicateur ou du levier
     */
    long _oldValue;
    /**
     * collection contenant les anciennes valeurs des AbstractElement
     */
    private ArrayList<Long> _history = new ArrayList<Long>();

    /**
     * Cette méthode permet de créer une instance de la classe AbstractElement
     * @param name le nom de l'AbstractElement
     * @param value la valeur de l'AbstractElement
     */
    public AbstractElement(String name, long value){
        _name = name;
        _value = value;
        _history.add(value);
    }

    /**
     * Méthode permettant de changer la valeur de l'AbstractElement avec une nouvelle valeur
     * @param value la nouvelle valeur de l'indicateur/levier
     */
    public void setValue(long value){
        _oldValue = _value;
        _value = value;
        notifyObservers();
    }

    /**
     * Méthode permettant de renvoyer l'ancienne valeur de l'AbstractElement
     * @return l'ancienne valeur de l'indicateur/levier
     */
    public long getOldValue(){
        return _oldValue;
    }

    /**
     * Méthode permettant de renvoyer la valeur acutelle de l'AbstractElement
     * @return la valeur actuelle de l'AbstractElement
     */
    public long getValue(){
        return _value;
    }

    public long getLastValue(){
        return _history.get(Semestre.getInstance().getSemestre()-1);
    }

    /**
     * Méthode permettant de renvoyer le nom de l'AbstractElement
     * @return le nom de l'indicateur ou du levier
     */
    public String get_name() {
        return _name;
    }

    /**
     * Méthode renvoyant la collection contenant l'historique des valeurs de l'AbstractElement
     * @return collection des valeurs de l'AbstractElement
     */
    public Collection<Long> get_history() {
        return _history;
    }

    /**
     * Méthode permettant de passer au semestre suivant
     */
    @Override
    public void ClockForvard() {
        _history.add(this.getValue());
        notifyObservers();
    }
}
