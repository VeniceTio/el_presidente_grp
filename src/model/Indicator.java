package model;

import java.util.HashMap;
import java.util.Map;

public class Indicator extends AbstractElement{
    /**
     * Map contenant les indicateurs
     */
    private Map<AbstractElement, String> _formula = new HashMap<AbstractElement, String>();
    /**
     * Attribut contenant la formule de l'indicateur
     */
    private AbstractFormula _maj;
    /**
     * Attribut permettant de savoir si l'indicateur statique ou pas (lors du clockForward: passage au semestre suivant, ce booléen permettra de savoir on met à jour l'indicateur ou pas)
     */
    private Boolean _statique;
    /**
     * Attribut contenant le type de l'indicateur (nombre ou pourcentage)
     */
    private IndicatorType _type;

    /**
     * Méthode permettant de créer une instance de la classe Indicator
     * @param name le nom de l'indicateur
     * @param value la valeur de l'indicateur
     * @param maj la formule de l'indicateur
     * @param status paramètre permettant de savoir si l'indicateur statique ou pas (lors du clockForward: passage au semestre suivant, ce booléen permettra de savoir on met à jour l'indicateur ou pas)
     */
    public Indicator(String name, int value, AbstractFormula maj, Boolean status, IndicatorType type){
        super(name,value);
        _maj = maj;
        _statique = status;
        _type = type;
    }

    /**
     * Méthode permettant d'ajouter un facteur à la liste des éléments composant la formule
     * @param ab l'AbstractElement qui va jouer sur le this
     * @param action la manière dont il va jouer
     */
    public void addFacteur(AbstractElement ab, String action){
        this._formula.put(ab,action);
    }

    /**
     * Méthode permettant de renvoyer la formule
     * @return la formule
     */
    public Map<AbstractElement, String> getFormula(){return _formula;}

    /**
     * Méthode permettant de mettre à jour la valeur de l'indicateur à partir d'un levier
     * @param lever le levier qui agit sur l'indicateur
     */
    public void MajOneValue(Lever lever){
        _maj.updateByOneLever(this, lever);
    }

    /**
     * Met à jour toutes les valeurs de l'indicateurs
     */
    public void initValue(){
        _maj.updateByLevers(this);
        addToHystory(this.getValue());
    }

    /**
     * Méthode permettant d'afficher le nom et la valeur de l'indicateur
     * @return la chaîne affichant le nom et la valeur
     */
    public String toString(){return this.get_name() + " : "+ this.getValue();}

    /**
     * Méthode permettant de récupérer le type de l'indicateur
     * @return IndicatorType type de l'indicateur
     */
    public IndicatorType getType(){return this._type;}

    /**
     * Méthode permettant de passer au semestre suivant
     */
    public void ClockForvard(){
        if (_statique){
            initValue();
        }
        notifyObservers();

    }
}
