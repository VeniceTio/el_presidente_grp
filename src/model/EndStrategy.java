package model;

public interface EndStrategy {

    /**
     * Méthode contenant la méthode dont la fin de partie est déterminée
     * @return True si condition d'arret atteinte sinon False
     */
    public boolean check();

}
