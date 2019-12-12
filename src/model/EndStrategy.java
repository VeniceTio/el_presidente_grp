package model;

public interface EndStrategy {

    /**
     * verifie la condition d'arret
     * @return True si condition d'arret atteint sinon false
     */
    public boolean check();

}
