package model;

import controller.ElementControl;

import java.util.ArrayList;

public class DeadEnd extends EndStrategy{


    /**
     * liste contenant tous les indicateurs ayant fait perdre
     */
    ArrayList<Indicator> _liste = new ArrayList<>();

    /**
     * Récupération de l'instance de l'ElementControl (controleur principal)
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
     * @return _liste L'indicateur sur lequel le levier agit
     */
    public ArrayList<Indicator> getListe(){
        return _liste;
    }

    /**
     * Méthode permettant de définir la méthode dont la fin de la partie est vérifiée
     * @return True si la partie est censée se terminer sinon False
     */
    @Override
    public boolean check() {
        boolean res = false;

        // On vérifie qu'aucun indicateur ne soit trop bas
        for (Indicator indicator:
             ec.getIndicators()) {
            if (indicator.getValue()<5 &&
                    indicator.get_name()!="charge de travail" &&
                    indicator.get_name()!="nombre de prix nobel"){
                res=true;
                _liste.add(indicator);
            }
        }

        // Le joueur a réussit à tenir 32 semestres
        if (Semestre.getInstance().getSemestre()==32){
            _win = true;
            res = true;
        }
        return res;
    }

}
