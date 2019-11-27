package utils;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {
    /**
     * Collection contenant tous les observers
     */
    private Collection<ElementObserver> _observers;

    /**
     * Méthode qui est le constructeur de la classe
     */
    public Observable() {
        _observers = new ArrayList<>();
    }

    /**
     * Méthode permettant d'ajouter un nouvelle observer
     * @param observer
     */
    public void add(ElementObserver observer) {
        _observers.add(observer);
    }

    /**
     * Méthode permettant de notifier tous les observers
     */
    public void notifyObservers() {  //DETERMINER LES PARAMETER
        for (ElementObserver observer : _observers)
            observer.update();
    }
}
