package model;

import java.util.ArrayList;
import java.util.Collection;

public class LeverFamily {
    /**
     * Nom de la famille de leviers
     */
    private String _name;
    /**
     * Liste contenant tous les leviers de la famille
     */
    private Collection<Lever> _levers = new ArrayList<Lever>();

    /**
     *  Constructeur de la classe LeverFamily
     * @param name Le nom de la famille de leviers
     */
    public LeverFamily(String name){_name = name;}

    /**
     * Méthode permettant d'ajouter un levier à la famille
     * @param lever Levier à ajouter à la famille
     */
    public void addLever(Lever lever){
        _levers.add(lever);
    }

    /**
     * Méthode renvoyant la liste des famillles de leviers existantes
     * @return La liste des familles de leviers existantes
     */
    public Collection<Lever> getLevers(){
        return _levers;
    }

    /**
     * Méthode renvoyant le nom de la famille de levier
     * @return Le nom de la famille de levier
     */
    public String getName(){
        return _name;
    }
}
