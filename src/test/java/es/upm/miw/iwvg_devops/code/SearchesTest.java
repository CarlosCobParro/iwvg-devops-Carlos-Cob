package es.upm.miw.iwvg_devops.code;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SearchesTest {

    @Test
    void testFindUserFamilyNameByUserNameDistinct() {
        assertEquals(List.of("Blanco", "Torres", "Lopez"), new es.upm.miw.iwvg_devops.code.Searches()
                .findUserFamilyNameByUserNameDistinct("Antonio")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindUserFractionNumeratorByFamilyName() {
        assertEquals(List.of(2, 4, 0, 1, 1,0,1,1), new es.upm.miw.iwvg_devops.code.Searches().findFractionNumeratorByUserFamilyName("Torres")
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFamilyNameByFractionDenominator() {
        assertEquals(List.of("Lopez"), new Searches().findUserFamilyNameByFractionDenominator(2)
                .collect(Collectors.toList()));
    }
    @Test
    void testfindUserNameInitialByAnyProperFraction() {
        assertEquals(List.of("Ana", "Oscar", "Antonio", "Paula", "Antonio", "Antonio"), new es.upm.miw.iwvg_devops.code.Searches().findUserNameInitialByAnyProperFraction(2)
                .collect(Collectors.toList()));
    }

    @Test
    void testfindUserIdByAnyProperFraction() {
        assertEquals(List.of("2", "3", "5", "6","7","8"), new es.upm.miw.iwvg_devops.code.Searches().findUserIdByAnyProperFraction(2)
                .collect(Collectors.toList()));
    }
    @Test
    void testFindUserNameByAnyImproperFraction() {
        assertEquals(List.of("Fernandez", "Blanco", "Lopez", "Torres" ), new es.upm.miw.iwvg_devops.code.Searches().findUserNameByAnyImproperFraction(2)
                .collect(Collectors.toList()));
    }
    @Test
    void testFindFirstDecimalFractionByUserName() {
        assertEquals(List.of(1.0, 1.0, 2.0 ), new es.upm.miw.iwvg_devops.code.Searches().findFirstDecimalFractionByUserName("Pedro")
                .collect(Collectors.toList()));
    }


    void testFindUserFamilyNameInitialByAnyProperFraction() {
        assertEquals(List.of("Fernandez", "Blanco", "Lopez"), new es.upm.miw.iwvg_devops.code.Searches().findUserFamilyNameInitialByAnyProperFraction(2)
                .collect(Collectors.toList()));
    }

    @Test
    void testFindFirstFractionDivisionByUserId() {
        Fraction fraction = new Fraction(1, 2);
        assertTrue(new es.upm.miw.iwvg_devops.code.Searches().findFirstFractionDivisionByUserId("1").isEquivalent(fraction));

    }
    @Test
    void testFindDecimalFractionByNegativeSignFraction() {
        assertEquals(List.of(-0.2, -0.5, -0.5 ), new es.upm.miw.iwvg_devops.code.Searches().findDecimalFractionByNegativeSignFraction(2)
                .collect(Collectors.toList()));
    }
    @Test
    void testFindHighestFraction() {
        Fraction fraction = new Fraction(8, 1);
        assertTrue(new es.upm.miw.iwvg_devops.code.Searches().findHighestFraction().isEquivalent(fraction));

    }


}

