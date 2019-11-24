package Uttilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndicatorInfoTest {
    info indicatorInfo;
    @BeforeEach
    void setUp() {
        indicatorInfo = new info();
    }

    @Test
    void getIndicatorInfo() {
        String expected = "Indicateur représentant la satisfaction générale des étudiant(e)s";
        String actual = indicatorInfo.getIndicatorInfo("satisfaction étudiante");
        assertEquals(expected, actual);
    }

    @Test
    void getLeverInfo() {
        String expected = "Levier (indicateur) représentant la qualité de la formation\n"
                        + "Ce levier agit de manière négative et positive, c'est-à-dire qu'il peut faire baisser ou augmenter l'indicateur qu'il influence";
        String actual = indicatorInfo.getLeverInfo("satisfaction étudiante", "qualité de la formation");
        assertEquals(expected, actual);
    }
}