package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NombreTest {

    /**
     * Méthode de test s'assurant que la valeur renvoyée soit la bonne
     */
    @Test
    void courbeInter1() {
        long nbr;

        nbr = Nombre.courbeInter1(-32);
        assertTrue(nbr==0);

        nbr = Nombre.courbeInter1(0);
        assertTrue(nbr==0);

        nbr = Nombre.courbeInter1(10);
        assertTrue(nbr>=2500);
        assertTrue(nbr<=8750);

        nbr = Nombre.courbeInter1(50);
        assertTrue(nbr>=10000);
        assertTrue(nbr<=30000);

        nbr = Nombre.courbeInter1(80);
        assertTrue(nbr>=20000);
        assertTrue(nbr<=40000);

        nbr = Nombre.courbeInter1(100);
        assertTrue(nbr>=28720);
        assertTrue(nbr<=50000);

        nbr = Nombre.courbeInter1(130);
        assertTrue(nbr>=28719);
        //assertTrue(nbr<=50001);
    }
}