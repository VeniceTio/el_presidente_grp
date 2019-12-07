package controller;

import model.*;
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
     * Map contenant tous les leviers et indicateurs de l'application
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
     * Attribut contenant l'instance de la classe
     */
    private static ElementControl _instance = null;
    /**
     * Constructeur de la classe ElementControl
     */
    private ElementControl(){}

    /**
     * Méthode permettant de créer l'instance de la classe
     * @return l'instance de la classe
     */
    public static ElementControl getInstance(){
        if(_instance == null){
            _instance = new ElementControl();
        }
        return _instance;
    }

    /**
     * Méthode permettant d'ajouter une famille de levier à la liste des familles de levier
     * @param fl la famille de levier qu'on doit ajouter
     */
    public void addFamilyLever(LeverFamily fl){
        _familyLevers.add(fl);
    }

    /**
     * Méthode permettant de renvoyer toutes les familles de leviers
     * @return les familles de leviers
     */
    public Collection<LeverFamily> getFamilyLevers() {
        return _familyLevers;
    }

    /**
     * Méthode permettant de renvoyer tous les indicateurs
     * @return une collection d'indicateurs
     */
    public ArrayList<Indicator> getIndicators() {
        return _indicators;
    }

    /**
     * Méthode permettant de renvoyer un indicateur ou un levier en fonction de son nom
     * @param name la nom de l'indicateur/levier qu'on doit retourner
     * @return l'indicateur/levier correspondant au nom du paramètre
     */
    public AbstractElement getElement(String name) {
        return _mapElement.get(name);
    }

    /**
     * Méthode permettant de créer un nouvelle indicateur
     * @param name le nom de l'indicateur
     * @param value la valeur de l'indicateur
     * @param af la formule utilisé pour l'indicateur qui vient d'être créer
     * @param boolStatic: paramètre permettant de savoir si l'indicateur statique ou pas (lors du clockForward: passage au semestre suivant, ce booléen permettra de savoir on met à jour l'indicateur ou pas)
     * @return l'indicateur qu'on vient de créer
     */
    public Indicator createIndicator(String name, int value, AbstractFormula af, boolean boolStatic){
        Indicator indicator = new Indicator(name, value, af, boolStatic);
        _mapElement.put(name, indicator);
        _elements.add(indicator);
        _indicators.add(indicator);
        return indicator;
    }

    /**
     * Méthode permettant de créer un nouveau levier
     * @param name le nom du levier
     * @param value la valeur du levier
     * @return le levier qu'on vient de créer
     */
    public Lever createLever(String name, int value){
        Lever lever = new Lever(name, value);
        _mapElement.put(name,lever);
        _elements.add(lever);
        return lever;
    }

    /**
     * Méthode permet de passer au semestre suivant
     */
    @Override
    public void ClockForvard() {
        Semestre.getInstance().ClockForvard();
        for(AbstractElement element : _elements){
            element.ClockForvard();
        }

    }
}
