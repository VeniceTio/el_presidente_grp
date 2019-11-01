package Model;

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
     * Attribut permettant de savoir si l'indicateur est statique ou pas
     */
    private Boolean _statique;

    /**
     * Méthode permettant de créer une instance de la classe Indicator
     * @param name le nom de l'indicateur
     * @param value la valeur de l'indicateur
     * @param maj la formule de l'indicateur
     * @param status paramètre permettant de si l'indicateur est statique ou pas
     */
    public Indicator(String name, int value, AbstractFormula maj, Boolean status){
        super(name,value);
        _maj = maj;
        _statique = status;
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
     * Méthode permettant de mettre à jour une valeur de l'indicateur à partir d'un levier
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
    }

    /**
     * Méthode renvoyant le nom et la valeur du levier
     * @return la chaîne affichant le nom et la valeur
     */
    public String toString(){return this.get_name() + " : "+ this.getValue();}

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
