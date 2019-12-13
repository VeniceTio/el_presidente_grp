package utils;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Observable {
    /**
     * Collection contenant tous les observers de l'élément Observable
     */
    private Collection<ElementObserver> _observers;

    /**
     * Constructeur de la classe Observable
     */
    public Observable() {
        _observers = new ArrayList<>();
    }

    /**
     * Méthode permettant d'ajouter un nouvelle observateur
     * @param observer Observateur à ajouter à l'élément Observable
     */
    public void add(ElementObserver observer) {
        _observers.add(observer);
    }

    /**
     * Méthode permettant de notifier tous les observateurs de l'objet Observable
     */
    public void notifyObservers() {
        for (ElementObserver observer : _observers)
            observer.update();
    }
}
