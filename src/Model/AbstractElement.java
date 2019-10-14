package Model;

import View.Observable;

import java.util.Collection;

public class AbstractElement extends Observable {
    private String _name;
    private int _value;
    private Collection<AbstractElement> _history;

    public AbstractElement(String name, int value){
        _name = name;
        _value = value;
    }
    public void setValue(int value){
        this._value = value;
        notifyObservers(value);
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
