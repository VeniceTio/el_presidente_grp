package Model;

import Uttilities.Observable;

import java.util.Collection;

public class AbstractElement extends Observable {
    private String _name;
    int _value;
    int _oldValue;
    private Collection<AbstractElement> _history;

    public AbstractElement(String name, int value){
        _name = name;
        _value = value;
    }
    public void setValue(int value){
        this._oldValue = _value;
        this._value = value;
        notifyObservers(value);
    }
    public int getOldValue(){
        return _oldValue;
    }
    public int getValue(){
        return this._value;
    }
    public String get_name() {
        return _name;
    }

    public Collection<AbstractElement> get_history() {
        return _history;
    }
}
