package Model;

import Uttilities.Observable;

import java.util.Collection;

public class AbstractElement extends Observable implements Time{
    private String _name;
    long _value;
    long _oldValue;
    private Collection<AbstractElement> _history;

    public AbstractElement(String name, long value){
        _name = name;
        _value = value;
    }
    public void setValue(long value){
        this._oldValue = _value;
        this._value = value;
        notifyObservers(value);
    }
    public long getOldValue(){
        return _oldValue;
    }
    public long getValue(){
        return this._value;
    }
    public String get_name() {
        return _name;
    }

    public Collection<AbstractElement> get_history() {
        return _history;
    }

    @Override
    public void ClockForvard() {

    }
}
