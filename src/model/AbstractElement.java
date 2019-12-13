package model;

import utils.Observable;

import java.util.ArrayList;
import java.util.Collection;

public class AbstractElement extends Observable implements Time{
    /**
     * Nom du levier ou de l'indicateur
     */
    private String _name;
    /**
     * Valeur actuelle de l'indicateur ou du levier
     */
    long _value;
    /**
     * Valeur à la période n-1 de l'indicateur ou du levier
     */
    long _oldValue;
    /**
     * Liste contenant toutes les précédentes valeurs prises par l'indicateur ou le levier
     */
    private ArrayList<Long> _history = new ArrayList<Long>();

    /**
     * Constructeur de la classe AbstractElement
     * @param name Le nom de l'AbstractElement
     * @param value La valeur de l'AbstractElement
     */
    public AbstractElement(String name, long value){
        _name = name;
        _value = value;
        _history.add(value);
    }

    /**
     * Méthode permettant de changer la valeur de l'AbstractElement avec une nouvelle valeur
     * @param value La nouvelle valeur de l'AbstractElement
     */
    public void setValue(long value){
        _oldValue = _value;
        _value = value;
        notifyObservers();
    }

    /**
     * Méthode renvoyant la dernière valeur de l'AbstractElement
     * @return La dernière valeur de l'AbstractElement
     */
    public long getOldValue(){
        return _oldValue;
    }

    /**
     * Méthode renvoyant la valeur acutelle de l'AbstractElement
     * @return La valeur actuelle de l'AbstractElement
     */
    public long getValue(){
        return _value;
    }

    /**
     * Méthode renvoyant la valeur de l'AbstractElement du semestre précédent
     * @return La valeur de l'AbstractElement du semestre précédent
     */
    public long getLastValue(){
        return _history.get(Semestre.getInstance().getSemestre()-1);
    }

    /**
     * Méthode renvoyant le nom de l'AbstractElement
     * @return Le nom de l'indicateur ou du levier
     */
    public String get_name() {
        return _name;
    }

    /**
     * Méthode renvoyant la Liste contenant toutes les précédentes valeurs prises par l'AbstractElement
     * @return Liste contenant toutes les précédentes valeurs prises par l'AbstractElement
     */
    public ArrayList<Long> get_history() {
        return _history;
    }

    /**
     * Méthode permettant d'ajouter une valeur à la liste des valeurs précédentes de l'AbstractElement
     */
    public void addToHistory(long value){
        _history.add(value);
    }

    /**
     * Méthode gérant le passage au semestre suivant
     */
    @Override
    public void ClockForvard() {
        addToHistory(this.getValue());
        notifyObservers();
    }
}
