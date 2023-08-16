package calendar.play;

import calendar.futharks.OldEnglish;
import calendar.futharks.Futharks;
import calendar.futharks.Rune;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class SundaySymbolTest extends TestCase {

    private SundaySymbol sundaySymbol;

    @Test
    public void testGetSymbol() {

        sundaySymbol = new SundaySymbol(Futharks.OLD_ENGLISH);

        // For 2023 the first sunday is 1st Jan, so expect same symbol as day 1
        Rune expected = OldEnglish.values()[0];

        assertEquals(expected, sundaySymbol.getSundayRune(LocalDate.of(2023, Month.SEPTEMBER, 4)));
        assertEquals(expected, sundaySymbol.getSundayRune(Month.JANUARY, 2023));

    }


    @Test
    public void testGetSymbolLeapYear() {

        sundaySymbol = new SundaySymbol(Futharks.OLD_ENGLISH);

        // 2024 is a leap year, so the symbol should change from day 7 to day 6
        LocalDate leapYear = LocalDate.of(2024, Month.JANUARY, 1);
        assertTrue(leapYear.isLeapYear());

        // For 2024 the first sunday is 7th Jan, so expect the symbol for day 7 before the leap and day 6 after the leap
        Rune beforeLeap = OldEnglish.values()[6];
        Rune afterLeap = OldEnglish.values()[5];

        assertEquals(beforeLeap, sundaySymbol.getSundayRune(LocalDate.of(2024, Month.FEBRUARY, 1)));
        assertEquals(beforeLeap, sundaySymbol.getSundayRune(Month.JANUARY, 2024));

        // After Feb 29th, the symbol should move back by a day
        assertEquals(afterLeap, sundaySymbol.getSundayRune(LocalDate.of(2024, Month.MARCH, 1)));
        assertEquals(afterLeap, sundaySymbol.getSundayRune(Month.SEPTEMBER, 2024));

    }
}