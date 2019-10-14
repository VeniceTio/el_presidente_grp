package Model;

import java.util.Collection;

public class AbstractElement {
    private String _name;
    private int _value;
    private Collection<AbstractElement> _history;

    public AbstractElement(String name, int value){
        _name = name;
        _value = value;
    }
    public void setValue(int value){
        this._value = value;
    }
    public int getValue(){
        return this._value;
    }
}
