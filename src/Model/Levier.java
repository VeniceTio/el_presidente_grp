package Model;

import java.util.ArrayList;
import java.util.Collection;

public class Levier extends AbstractElement{
    private Collection<Indicateur> _influencer = new ArrayList<Indicateur>();
    public Levier(String name,int value){
        super(name,value);
        int jav = 0;
    }
    public void addInfluencer(Indicateur indic){
        _influencer.add(indic);
    }

    public String toString(){return this.get_name() + " : "+this.getValue();}
    @Override
    public void setValue(long value){
        _oldValue = _value;
        _value = value;
        for (Indicateur indic:_influencer) {
            indic.MajOneValue(this);
        }
        notifyObservers(value);
    }
}
