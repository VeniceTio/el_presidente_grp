package controller;

import model.*;
import view.GameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ElementControl implements Time{
    /**
     * Liste contenant toutes les familles de levier
     */
    private Collection<LeverFamily> _familyLevers = new ArrayList<LeverFamily>();
    /**
     * HashMap contenant tous les leviers et indicateurs de l'application
     */
    private Map<String, AbstractElement> _mapElement = new HashMap<String, AbstractElement>();
    /**
     * Liste contenant les indicateurs/leviers de l'application
     */
    private ArrayList<AbstractElement> _elements = new ArrayList<AbstractElement>();
    /**
     * Liste contenant les indicateurs de l'application
     */
    private ArrayList<Indicator> _indicators = new ArrayList<Indicator>();
    /**
     * Liste contenant les leviers de l'application
     */
    private ArrayList<Lever> _levers = new ArrayList<Lever>();
    /**
     * Stratégie courante (représente le scénario de la partie)
     */
    private EndStrategy _end = null;
    /**
     * L'instance de la classe ElementControl (Singleton)
     */
    private static ElementControl _instance = null;

    /**
     * Constructeur de la classe ElementControl
     */
    private ElementControl(){}

    /**
     * Méthode permettant de créer l'instance de la classe ElementControl
     * @return L'instance de la classe
     */
    public static ElementControl getInstance(){
        if(_instance == null){
            _instance = new ElementControl();
        }
        return _instance;
    }

    /**
     * Méthode permettant d'ajouter une famille de levier à la liste des familles de levier
     * @param fl Le nom de la famille de levier que l'on souhaite ajouter
     */
    public void addFamilyLever(LeverFamily fl){
        _familyLevers.add(fl);
    }

    /**
     * Méthode renvoyant toutes les familles de leviers existantes
     * @return La liste des familles de leviers
     */
    public Collection<LeverFamily> getFamilyLevers() {
        return _familyLevers;
    }

    /**
     * Méthode renvoyant tous les indicateurs existants
     * @return La liste des indicateurs
     */
    public ArrayList<Indicator> getIndicators() {
        return _indicators;
    }

    /**
     * Méthode renvoyant tous les leviers existants
     * @return La liste des leviers
     */
    public ArrayList<Lever> getLevers() { return _levers; }

    /**
     * Méthode permettant de renvoyer un indicateur ou un levier en fonction de son nom
     * @param name Le nom de l'indicateur/levier qu'on souhaite récupérer
     * @return L'indicateur/levier correspondant au nom du paramètre
     */
    public AbstractElement getElement(String name) {
        return _mapElement.get(name);
    }

    /**
     * Méthode permettant de créer un nouvel indicateur
     * @param name Le nom de l'indicateur
     * @param value La valeur de l'indicateur
     * @param af La formule permettant le calcul de la valeur de l'indicateur
     * @param boolStatic: Le statut statique ou non de l'indicateur (utilisé pour le ClockForward())
     * @return L'indicateur venant d'être créé
     */
    public Indicator createIndicator(String name, int value, AbstractFormula af, boolean boolStatic, IndicatorType type){
        Indicator indicator = new Indicator(name, value, af, boolStatic, type);
        _mapElement.put(name, indicator);
        _elements.add(indicator);
        _indicators.add(indicator);
        return indicator;
    }

    /**
     * Méthode permettant de créer un nouveau levier
     * @param name Le nom du levier
     * @param value La valeur du levier
     * @return Le levier venant d'être créé
     */
    public Lever createLever(String name, int value, long scale){
        Lever lever = new Lever(name, value, scale);
        _mapElement.put(name,lever);
        _elements.add(lever);
        _levers.add(lever);
        return lever;
    }

    /**
     * Méthode renvoyant la stratégie courante (représente le Scénario de la partie)
     * @return La stratégie courante
     */
    public EndStrategy getEnd() {
        return _end;
    }

    /**
     * Méthode permettant de changer la stratégie courante (paramètrage de la fin du jeu)
     */
    public void setEnd(EndStrategy end){
        _end = end;
    }

    /**
     * Méthode renvoyant Vrai ou Faux en fonction de si la partie est censée se terminer ou non
     * @return True si la partie odit se terminer, False sinon
     */
    private boolean checkFin(){
        return _end.check();
    }

    /**
     * Méthode permettant de passer au semestre suivant
     */
    @Override
    public void ClockForvard() {
        Semestre.getInstance().ClockForvard();
        for(AbstractElement element : _elements){
            element.ClockForvard();
        }

        if (checkFin()) {
            try {
                GameView.getInstance().goToEndView();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
