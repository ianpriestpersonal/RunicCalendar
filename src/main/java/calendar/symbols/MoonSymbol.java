package calendar.symbols;

import calendar.calculations.CalendarAstronomer;
import calendar.futharks.Rune;

import java.time.*;
import java.util.*;

public class MoonSymbol {

    private final Rune[] futhark;

    public MoonSymbol(Rune[] futhark) {
        this.futhark = futhark;

    }

    /**
     * Get the Rune for the next new moon on this date.
     * <p>
     * Get the next year in a 19 year cycle that there will be a new moon on this day/month then convert it
     * to a rune
     *
     * @param date the date
     * @return the year rune for the next year when there is a new moon,or empty optional if no new moon
     */
    public Optional<Rune> getMoonRune(LocalDate date) {

        if ( date.getYear() < 2014 )
            throw new IllegalArgumentException("Year too early");

        return getNewMoonYear(date)
                .map(MoonSymbol::getMagicNumber)
                .map(this::getRune);

    }

    /**
     * Get the next year within a 19 year cycle that there is a new moon on the given day
     *
     * @param date the date
     * @return the next year there will be a new moon on that day/month in the next 19 years
     */
    public static Optional<Integer> getNewMoonYear(LocalDate date) {

        for( int i=0; i<19; i++ ) {

            LocalDate plusYears = date.plusYears(i);
            LocalDate newMoonDate = getNextNewMoon(plusYears);
            if ( plusYears.equals(newMoonDate) ) {
                return Optional.of(plusYears.getYear());
            }
        }

        return Optional.empty();
    }

    /**
     * Get the magic number 0-18 for a year.
     *
     * Note that the Runes give a magic number indexed from 1, so add 1 to this to match {@link Rune#magicNumber()}
     *
     * @param year the year
     * @return the magic number for that year
     */
    protected static int getMagicNumber(int year) {
        return (year - 2014) % 19;
    }

    /**
     * Get the moon rune for a given year
     *
     * @param year
     * @return
     */
    public Rune getMoonRune(int year) {
        return getRune(getMagicNumber(year));
    }

    /**
     * Get the moon phase as a double and as an int.
     * The phase is a double from 0-0.9999999 where
     * 0.0 = new moon
     * 0.25 = first quarter
     * 0.5 = full moon
     * 0.75 = last quarter
     *
     * The ordinal is a 28-step integer calculated from the phase with a range of 0-27 where
     *
     * 0 = new moon
     * 7 = first quarter
     * 14 = full moon
     * 21 = last quarter
     *
     * @param date
     * @return MoonPhase with phase and ordinal at midday on the given date
     */
    public static MoonPhase getMoonPhase(LocalDate date) {

        final Date midday = Date.from(date.atTime(12, 0)
                .atZone(ZoneId.systemDefault())
                .toInstant());

        CalendarAstronomer ca = new CalendarAstronomer(midday);

        double phase = ca.getMoonPhase();
        int ordinal = getMoonOrdinal(phase);
        return new MoonPhase(phase, ordinal);
    }

    /**
     * Get the rune (one of 19) for a magicNumber (0-18)
     * @param magicNumber 0-18
     * @return the rune for the number
     */
    protected Rune getRune(int magicNumber) {
        return futhark[magicNumber];
    }

    /**
     * Get the moon runes for the given month for 19 years starting at the given year.
     *
     * The returned list will be the same length as there are days in the month, except for a leap year
     * when Feb will still only be 28 days. Each list position gives the date in the month.
     *
     * @param month the month
     * @param year the year to start from
     * @return list of runes, empty optional if there is no new moon on a given date
     */
    public List<Optional<Rune>> getMoonSymbolsForMonth(Month month, int year) {

        if ( year < 2014 )
            throw new IllegalArgumentException("Year too early");

        int lengthOfMonth = YearMonth.of(year, month).lengthOfMonth();
        LocalDate date = LocalDate.of(year, month, 1);

        Optional<Rune>[] symbols = new Optional[lengthOfMonth];
        for (int i = 0; i < lengthOfMonth; i++) {

            LocalDate plusDays = date.plusDays(i);
            symbols[i] = getNewMoonYear(plusDays)
                    .map(MoonSymbol::getMagicNumber)
                    .map(this::getRune);
        }

        return Arrays.asList(symbols);

    }

    public static LocalDate getNextNewMoon(LocalDate date) {

        CalendarAstronomer ca = new CalendarAstronomer(date);

        return new Date(ca.getMoonTime(CalendarAstronomer.NEW_MOON, true)).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

    }

    /**
     * Splits the moon phase into 28 ordinal steps.
     *
     * 0 = new moon
     * 7 = first quarter
     * 14 = full moon
     * 21 = last quarter
     *
     * based on the CalendarAstronomer moonPhase of 0 = new moon, 0.5=full moon
     *
     *
     * @return int range 0-27, the ordinal for the supplied date
     */
    private static int getMoonOrdinal(double moonPhase) {

        long ordinal = (long) Math.floor((moonPhase+halfWidth)*28);
        // Adding the halfWidth slide can take it over 27, which is a wrap back to 0
        return ordinal < 28 ? (int) ordinal : 0;

    }

    private static final double halfWidth = 1.0/56.0;
}
