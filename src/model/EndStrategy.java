package model;

public abstract class EndStrategy {
    /**
     * Booléen déterminant si la partie est gagnée ou non
     */
    boolean _win = false;
    /**
     * Méthode contenant la méthode dont la fin de partie est déterminée
     * @return True si condition d'arret atteinte sinon False
     */
    public abstract boolean check();
    public boolean getWin(){return _win;};
}
