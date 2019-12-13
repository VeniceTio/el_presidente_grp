package model;

public interface Time {
    /**
     * Nombre de semestre
     */
    public int _nbrSemestre = 0;

    /**
     * Méthode gérant le passage au semestre suivant
     */
    public void ClockForvard();
}
