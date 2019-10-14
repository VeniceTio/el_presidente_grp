package View;

import Model.AbstractElement;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {
    private Collection<ElementObserver> _observers;

    public Observable() {
        _observers = new ArrayList<>();
    }

    public void add(ElementObserver observer) {
        _observers.add(observer);
    }

    public void notifyObservers(int value) {  //DETERMINER LES PARAMETER
        for (ElementObserver observer : _observers)
            observer.update(value);
    }
}
