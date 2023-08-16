package calendar.play;

import calendar.futharks.OldEnglish;
import calendar.futharks.Futharks;
import calendar.futharks.Rune;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MoonSymbolTest extends TestCase {

    private static final int[] JANUARY_FULL_MOON_YEARS = {
            2033, 2022, 0, 2030, 2038, 0, 2027, 0, 2035, 0, // 1-10
            2024, 2032, 2021, 2029, 0, 2037, 0, 2026, 0, 2034, // 11-20
            2023, 0, 2031, 2020, 0, 2028, 0, 2036, 2025, 2033, // 21-30
            0}; // 31

    @Test
    public void test24Jan2020() {
        MoonSymbol moonSymbol = new MoonSymbol(Futharks.OLD_ENGLISH);

        // January 24 2020 is a new moon, magic number 7
        Optional<Rune> magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 24));
        assertEquals(OldEnglish.values()[6], magicNumber.get());
    }

    public void testFiveDaysJan2020() {

        MoonSymbol moonSymbol = new MoonSymbol(Futharks.OLD_ENGLISH);

        // January 1 2020 = nope
        Optional<Rune> magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 1));
        assertEquals(OldEnglish.values()[0], magicNumber.get());
        // Jan 2 = 9
        magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 2));
        assertEquals(OldEnglish.values()[8], magicNumber.get());
        // jan 3 = nope
        magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 3));
        assertEquals(Optional.empty(), magicNumber);
        // jan 4 = 17
        magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 4));
        assertEquals(OldEnglish.values()[16], magicNumber.get());
        // jan 5 = 6
        magicNumber = moonSymbol.getMoonRune(LocalDate.of(2020, Month.JANUARY, 5));
        assertEquals(OldEnglish.values()[5], magicNumber.get());
    }

    @Test
    public void testGetYearsJanuary() {

        LocalDate date = LocalDate.of(2020, Month.JANUARY, 1);
        for (int i = 0; i < 31; i++) {

            int newMoonYear = MoonSymbol.getNewMoonYear(date.plusDays(i)).orElse(0);
            int expected = JANUARY_FULL_MOON_YEARS[i];
            assertEquals(expected, newMoonYear);
        }
    }

    @Test
    public void testJanuary2() {

        MoonSymbol moonSymbol = new MoonSymbol(Futharks.OLD_ENGLISH);

        Optional<Rune>[] syms = new Optional[31];
        for (int i=0; i<31; i++ ) {
            int year = JANUARY_FULL_MOON_YEARS[i];
            syms[i] = year != 0 ? Optional.of(moonSymbol.getRune(MoonSymbol.getMagicNumber(year))) : Optional.empty();
        }
        List<Optional<Rune>> expected = Arrays.asList(syms);

        List<Optional<Rune>> moonSymbolsForMonth = moonSymbol.getMoonSymbolsForMonth(Month.JANUARY, 2020);
        assertEquals(expected, moonSymbolsForMonth);
    }

}