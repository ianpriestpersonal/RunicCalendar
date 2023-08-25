package calendar.symbols;

import calendar.futharks.OldEnglish;
import calendar.futharks.Futharks;
import calendar.futharks.Rune;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.Month;
import java.util.List;

public class DaySymbolTest extends TestCase {

    @Test
    public void testGetJan() {

        DaySymbol daySymbol = new DaySymbol(Futharks.OLD_ENGLISH);

        // Feb should start at 1 and end at 3
        List<Rune> daySymbolsForMonth = daySymbol.getDayRunesForMonth(Month.JANUARY);
        assertEquals(31, daySymbolsForMonth.size());
        assertEquals(OldEnglish.values()[0], daySymbolsForMonth.get(0));
        assertEquals(OldEnglish.values()[2], daySymbolsForMonth.get(30));
    }

    @Test
    public void testGetFeb() {

        DaySymbol daySymbol = new DaySymbol(Futharks.OLD_ENGLISH);

        // Feb should start at 4 and end at 3
        List<Rune> daySymbolsForMonth = daySymbol.getDayRunesForMonth(Month.FEBRUARY);
        assertEquals(28, daySymbolsForMonth.size());
        assertEquals(OldEnglish.values()[3], daySymbolsForMonth.get(0));
        assertEquals(OldEnglish.values()[2], daySymbolsForMonth.get(27));
    }

    @Test
    public void testGetJuly() {

        DaySymbol daySymbol = new DaySymbol(Futharks.OLD_ENGLISH);

        // Feb should start at 7 and end at 2
        List<Rune> daySymbolsForMonth = daySymbol.getDayRunesForMonth(Month.JULY);
        assertEquals(31, daySymbolsForMonth.size());
        assertEquals(OldEnglish.values()[6], daySymbolsForMonth.get(0));
        assertEquals(OldEnglish.values()[1], daySymbolsForMonth.get(30));
    }

}