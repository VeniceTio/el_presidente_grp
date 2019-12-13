package model;

import utils.Observable;

public class Semestre extends Observable implements Time{
    /**
     * Le nombre de semestres écoulés dans la partie
     */
    private int _nbrSemestre = 0;
    /**
     * Instance de la classe Semestre
     */
    private static Semestre Instance = null;

    /**
     * Constructeur de la classe Semestre
     */
    private Semestre(){}

    /**
     * Méthode renvoyant l'instance de la classe Semestre
     * @return L'instance de la classe Semestre
     */
    public static Semestre getInstance(){
        if(Instance == null){
            Instance = new Semestre();
        }
        return Instance;
    }

    /**
     * Méthode renvoyant le nombre de semestres écoulés
     * @return Le nombre de semestre écoulés
     */
    public int getSemestre(){return _nbrSemestre;}

    /**
     * Méthode gérant le passage au semestre suivant
     */
    @Override
    public void ClockForvard() {
    _nbrSemestre++;
    notifyObservers();
    }

    /**
     * Méthode permettant d'afficher le nombre de semestre en cours
     * @return la chaîne de caractères contenant le nom et la valeur
     */
    public String toString(){return "Semestre n°"+_nbrSemestre;}
}
