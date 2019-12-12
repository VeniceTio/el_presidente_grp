package model;

import controller.ElementControl;

import java.util.ArrayList;

public class DeadEnd implements EndStrategy{
    /**
     * attribut contenant le fait si la partie est gagné ou perdu
     */
    boolean _win = false;

    /**
     * liste contenant tous les indicateurs ayant fait perdre
     */
    ArrayList<Indicator> _liste = new ArrayList<>();

    /**
     * contient toute les données de jeu
     */
    ElementControl ec = ElementControl.getInstance();

    /**
     * renvoie l'attribut _win
     * @return True si la partie est gagné sinon False
     */
    public boolean getWin(){
        return _win;
    }

    public ArrayList<Indicator> getListe(){
        return _liste;
    }

    @Override
    public boolean check() {
        boolean res = false;
        for (Indicator indicator:
             ec.getIndicators()) {
            if (indicator.getValue()<5 &&
                    indicator.get_name()!="charge de travail" &&
                    indicator.get_name()!="nombre de prix nobel"){
                res=true;
                _liste.add(indicator);
            }
        }
        if (Semestre.getInstance().getSemestre()==32){
            _win = true;
            res = true;
        }
        return res;
    }

}
