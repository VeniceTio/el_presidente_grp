package observateur;

import Model.AbstractElement;
import view.provisoryView;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {
    private Collection<AbstractElement> _observers;

    public Observable() {
        _observers = new ArrayList<>();
    }

    public void add(AbstractElement observer) {
        _observers.add(observer);
    }

    public void notifyObservers() {  //DETERMINER LES PARAMETER
        for (AbstractElement observer : _observers)
            observer.update();
    }
}
