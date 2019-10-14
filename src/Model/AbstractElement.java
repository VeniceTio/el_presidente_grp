package Model;

import java.util.Collection;

public class AbstractElement {
    private String _name;
    private int _etat;
    private Collection<AbstractElement> _history;

    public AbstractElement(String name, int etat){
        _name = name;
        _etat = etat;
    }
}
