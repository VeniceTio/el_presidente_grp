package model;

import java.util.ArrayList;
import java.util.Collection;

public class LeverFamily {
    /**
     * Nom de la famille de levier
     */
    private String _name;
    /**
     * Liste contenant tous les leviers
     */
    private Collection<Lever> _levers = new ArrayList<Lever>();

    /**
     * Méthode permettant de créer une instance de la classe LeverFamily
     * @param name le nom de la famille de levier
     */
    public LeverFamily(String name){_name = name;}

    /**
     * Méthode permettant d'ajouter un levier à la liste
     * @param lever
     */
    public void addLever(Lever lever){
        _levers.add(lever);
    }

    /**
     * Méthode permettant de renvoyer la liste de famillle de leviers
     * @return la liste de famille de leviers
     */
    public Collection<Lever> getLevers(){
        return _levers;
    }

    /**
     * Méthode permettant de renvoyer le nom de la famille de levier
     * @return le nom de la famille de levier
     */
    public String getName(){
        return _name;
    }
}
