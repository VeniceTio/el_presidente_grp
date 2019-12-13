package model;

import controller.ElementControl;

import java.util.ArrayList;

public class RepRecEnd8 implements EndStrategy{
    /**
     * Booléen variant en fonction de si la partie est gagnée ou non
     */
    boolean _win = false;

    /**
     * liste contenant tous les indicateurs ayant fait perdre
     */
    ArrayList<Indicator> _liste = new ArrayList<>();

    /**
     * Récupération de l'instance d'ElementControl (controlleur principal)
     */
    ElementControl ec = ElementControl.getInstance();

    /**
     * Méthode renvoyant Vrai ou Faux en fonction de si la partie est gagnée ou non
     * @return True si la partie est gagnée sinon False
     */
    public boolean getWin(){
        return _win;
    }

    /**
     * Méthode renvoyant la liste des indicateurs ayant fait perdre
     * @return Liste des indicateurs ayant fait perdre
     */
    public ArrayList<Indicator> getListe(){
        return _liste;
    }

    /**
     * Méthode déterminant la manière dont la fin de partie est déterminée
     * @return True si la partie est censée être terminée, False sinon
     */
    @Override
    public boolean check() {
        boolean res = false;

        // On s'assure qu'aucun indicateur ne soit trop bas
        for (Indicator indicator:
                ec.getIndicators()) {
            if (indicator.getValue()<4 &&
                    indicator.get_name()!="charge de travail" &&
                    indicator.get_name()!="nombre de prix nobel"){
                res=true;
                _liste.add(indicator);
            }
        }

        // La réputation de la recherche est bien à +45% au bout des 9 semestres ?
        if (Semestre.getInstance().getSemestre()==9){
            res=true;
            if(ec.getElement("réputation de la recherche").getValue()>45){
                _win = true;
            }
        }
        return res;
    }

}
