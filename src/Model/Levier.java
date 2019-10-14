package Model;

import java.util.Collection;

public class Levier extends AbstractElement{
    private Collection<Indicateur> _influencer = new arrayList<Indicateur>();
    public Levier(String name,int value){
        super(name,value);
        int jav = 0;
    }
    public void addInfluencer(Indicateur indic){
        _influencer.add(indic);
    }
    @Override
    public void set_influencer(int value){
        this._value = value;
        notifyObservers(value);
        f
    }
}
