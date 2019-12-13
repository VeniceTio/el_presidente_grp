package utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InfoTest {
    /**
     * Instance de la classe Info
     */
    Info _info;

    /**
     * Méthode de test permettant de mettre en place les éléments requis
     */
    @BeforeEach
    void setUp() {
        _info = new Info();
    }

    /**
     * Méthode de test s'assurant que la méthode fonctionne comme voulu
     */
    @Test
    void getIndicatorIndex() {
        int expected = 2;
        int actual = _info.getIndicatorIndex("nombre d'article publié");
        assertEquals(expected, actual);
    }

    /**
     * Méthode de test s'assurant que la méthode fonctionne comme voulu
     */
    @Test
    void getLeverIndex() {
        int expected = 5;
        int actual = _info.getLeverIndex("taux de réussite");
        assertEquals(expected, actual);
    }

    /**
     * Méthode de test s'assurant que la méthode fonctionne comme voulu
     */
    @Test
    void getLeverInfluence() {
        String expected = "(-/+)";
        String actual = _info.getLeverInfluence(0);
        assertEquals(expected, actual);
    }

    /**
     * Méthode de test s'assurant que la méthode fonctionne comme voulu
     */
    @Test
    void getIndicatorInfo() {
        String expected = "Indicateur représentant la satisfaction générale des étudiant(e)s";
        String actual = _info.getIndicatorInfo("satisfaction étudiante");
        assertEquals(expected, actual);
    }

    /**
     * Méthode de test s'assurant que la méthode fonctionne comme voulu
     */
    @Test
    void getLeverInfo() {
        String expected = "Levier (indicateur) représentant la satisfaction générale des professeurs qui influe sur\n"
                        + "• La recherche appliquée (-/+)" + "\n"
                        + "• La recherche fondamentale (-/+)" + "\n"
                        + "• Le taux de réussite (-/+)";
        String actual = _info.getLeverInfo("satisfaction professeur");
        assertEquals(expected, actual);
    }
}
