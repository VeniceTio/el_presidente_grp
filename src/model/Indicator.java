package model;

import java.util.HashMap;
import java.util.Map;

public class Indicator extends AbstractElement{
    /**
     * Map contenant les indicateurs
     */
    private Map<AbstractElement, String> _formula = new HashMap<AbstractElement, String>();
    /**
     * Formule de l'indicateur
     */
    private AbstractFormula _maj;
    /**
     * Statut statique ou non de l'indicateur (utilisé dans le ClockForward())
     */
    private Boolean _statique;
    /**
     * Type de l'indicateur (nombre ou pourcentage)
     */
    private IndicatorType _type;

    /**
     * Constructeur de la classe Indicator
     * @param name Le nom de l'indicateur
     * @param value La valeur de l'indicateur
     * @param maj La formule de l'indicateur
     * @param status Statut statique ou non de l'indicateur
     */
    public Indicator(String name, int value, AbstractFormula maj, Boolean status, IndicatorType type){
        super(name,value);
        _maj = maj;
        _statique = status;
        _type = type;
    }

    /**
     * Méthode permettant d'ajouter un facteur à la liste des éléments composants la formule
     * @param ab L'AbstractElement qui va influer sur la formule
     * @param action La manière dont il va influer
     */
    public void addFacteur(AbstractElement ab, String action){
        this._formula.put(ab,action);
    }

    /**
     * Méthode renvoyant la formule de l'indicateur
     * @return La formule de l'indicateur
     */
    public Map<AbstractElement, String> getFormula(){return _formula;}

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir d'un levier
     * @param lever Le levier qui agit sur l'indicateur
     */
    public void MajOneValue(Lever lever){
        _maj.updateByOneLever(this, lever);
    }

    /**
     * Méthode mettant à jour la valeur de l'indicateur
     */
    public void initValue(){
        _maj.updateByLevers(this);
        addToHistory(this.getValue());
    }

    /**
     * Méthode permettant d'afficher le nom et la valeur de l'indicateur
     * @return Une chaîne de caractère contenant le nom et la valeur de l'indicateur
     */
    public String toString(){return this.get_name() + " : "+ this.getValue();}

    /**
     * Méthode renvoyant le type de l'indicateur
     * @return Le type de l'indicateur
     */
    public IndicatorType getType(){return this._type;}

    /**
     * Méthode gérant le passage au semestre suivant
     */
    public void ClockForvard(){
        if (_statique){
            initValue();
        }
        notifyObservers();

    }
}
