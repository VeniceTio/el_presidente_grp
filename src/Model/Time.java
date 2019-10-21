package Model;

public interface Time {
    /**
     * Attribut contenant le nombre de semestre
     */
    public int _nbrSemestre = 0;

    /**
     * Méthode permettant de passer au semestre suivant
     */
    public void ClockForvard();
}
