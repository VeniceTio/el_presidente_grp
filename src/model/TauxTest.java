package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TauxTest {

    @BeforeEach
    void setUp() {
        Taux tx = new Taux();
    }

    @Test
    void updateByLevers() {
    }

    @Test
    void courbe1() {
        assertEquals(0,Taux.courbe1(-32,1));
        assertEquals(0,Taux.courbe1(0,1));
        assertEquals(50,Taux.courbe1(200000,1));
        assertEquals(97,Taux.courbe1(300000,1));
        assertEquals(100,Taux.courbe1(500000,1));
        assertEquals(100,Taux.courbe1(800000,1));
    }

    @Test
    void courbe2() {
        assertEquals(0,Taux.courbe2(-32,1));
        assertEquals(19,Taux.courbe2(300000,1));
        assertEquals(20,Taux.courbe2(300001,1));
        assertEquals(80,Taux.courbe2(500000,1));
        assertEquals(97,Taux.courbe2(700000,1));
        assertEquals(97,Taux.courbe2(700001,1));
        assertEquals(100,Taux.courbe2(1000000,1));
        assertEquals(100,Taux.courbe2(2000000,1));
    }

    @Test
    void courbe3() {
        assertEquals(0,Taux.courbe3(-32,1));
        assertEquals(0,Taux.courbe3(0,1));
        assertEquals(10,Taux.courbe3(100000,1));
        assertEquals(99,Taux.courbe3(400000,1));
        assertEquals(100,Taux.courbe3(700000,1));
        assertEquals(100,Taux.courbe3(800000,1));

    }

    @Test
    void courbe4() {
        assertEquals(0,Taux.courbe4(-32,1));
        assertEquals(0,Taux.courbe4(0,1));
        assertEquals(3,Taux.courbe4(4000,1));
        assertEquals(40,Taux.courbe4(6000,1));
        assertEquals(70,Taux.courbe4(10000,1));
        assertEquals(90,Taux.courbe4(12000,1));
        assertEquals(100,Taux.courbe4(22000,1));
        assertEquals(100,Taux.courbe4(25000,1));
    }
}