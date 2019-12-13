import model.NombreEleve;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NombreEleveTest {

    /**
     * Méthode de test s'assurant que la valeur renvoyée soit la bonne
     */
    @Test
    void courbe1() {
        Assertions.assertEquals(2, NombreEleve.courbe1(-32));
        assertEquals(1,NombreEleve.courbe1(400));
        assertEquals(0.5,NombreEleve.courbe1(1200));
        assertEquals(0.05,NombreEleve.courbe1(5000),0.001);
        assertEquals(0.003,NombreEleve.courbe1(10000),0.0001);
        assertEquals(0.0003,NombreEleve.courbe1(20000),0.00001);
        assertEquals(0.00003,NombreEleve.courbe1(30000),0.000001);
        assertEquals(0.00001,NombreEleve.courbe1(40000),0.000001);
        assertEquals(0.000009,NombreEleve.courbe1(50000),0.0000001);
        assertEquals(0.000003,NombreEleve.courbe1(60000),0.0000001);
        assertEquals(0.000002,NombreEleve.courbe1(70000),0.0000001);
    }
}