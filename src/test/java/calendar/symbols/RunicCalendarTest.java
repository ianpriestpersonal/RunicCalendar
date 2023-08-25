package calendar.symbols;

import calendar.futharks.Futharks;
import calendar.futharks.Rune;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.Month;
import java.util.List;

public class RunicCalendarTest extends TestCase {

    @Test
    public void testGetSunday() {
        RunicCalendar rc = new RunicCalendar(2020, Futharks.OLD_ENGLISH);
        List<Rune> sundayRunes = rc.getSundayRunes();
        assertEquals(2, sundayRunes.size());

    }

    @Test
    public void testGetJanuary() {
        RunicCalendar rc = new RunicCalendar(2014, Futharks.OLD_ENGLISH);
        List<RunicDay> calendar = rc.getCalendar(Month.JANUARY);
        for (int i = 0; i < calendar.size(); i++) {

            RunicDay day = calendar.get(i);
            String symbol = day.getDay().symbol();
            int magicNumber = day.getDay().magicNumber();

            String moon = day.getNewMoon().map(Rune::symbol).orElse(".");

            System.out.printf("[%d]: day=%d, symbol=%s, moon=%s\n", i+1, magicNumber, symbol, moon);
        }
    }

    @Test
    public void testAugust23OldEnglish() {

        RunicCalendar rc = new RunicCalendar(2023, Futharks.OLD_ENGLISH);
        List<RunicDay> calendar = rc.getCalendar(Month.AUGUST);
        for (int i = 0; i < calendar.size(); i++) {

            RunicDay day = calendar.get(i);
            String symbol = day.getDay().symbol();
            int magicNumber = day.getDay().magicNumber();

            String moon = day.getNewMoon().map(Rune::symbol).orElse(".");

            System.out.printf("[%d]: day=%d, symbol=%s, moon=%s\n", i+1, magicNumber, symbol, moon);
        }

        RunicDay sixteenth = calendar.get(15);
        // 16th Aug is a new moon in 2023
        Integer expectedMagicNumber = MoonSymbol.getMagicNumber(2023)+1;
        assertTrue(sixteenth.getNewMoon().isPresent());
        assertEquals(expectedMagicNumber, sixteenth.getNewMoon().map(Rune::magicNumber).get());
    }

    @Test
    public void testAugust23YoungerLongBranch() {

        RunicCalendar rc = new RunicCalendar(2023, Futharks.YOUNGER_LONG_BRANCH);
        List<RunicDay> calendar = rc.getCalendar(Month.AUGUST);
        for (int i = 0; i < calendar.size(); i++) {

            RunicDay day = calendar.get(i);
            String symbol = day.getDay().symbol();
            int magicNumber = day.getDay().magicNumber();

            String moon = day.getNewMoon().map(Rune::symbol).orElse(".");

            System.out.printf("[%d]: day=%d, symbol=%s, moon=%s\n", i+1, magicNumber, symbol, moon);
        }

        RunicDay sixteenth = calendar.get(15);
        // 16th Aug is a new moon in 2023
        Integer expectedMagicNumber = MoonSymbol.getMagicNumber(2023)+1;
        assertTrue(sixteenth.getNewMoon().isPresent());
        assertEquals(expectedMagicNumber, sixteenth.getNewMoon().map(Rune::magicNumber).get());
    }
}