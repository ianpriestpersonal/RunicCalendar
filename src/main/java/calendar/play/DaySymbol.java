package calendar.play;

import calendar.futharks.Rune;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Get the Rune for a given date.
 *
 * Each day in the Runic calendar has one of the seven day runes associated with it.
 * The symbol is fixed by date, i.e. the symbol for 1st August is always the same.
 *
 * No leap-years in this calendar!
 */
public class DaySymbol {

    private final Rune[] futhark;

    public DaySymbol(Rune[] futhark) {
        this.futhark = futhark;
    }

    private static final int[] DAY_ORD = {6, 0, 1, 2, 3, 4, 5};

    public Rune getRune(LocalDate date) {
        return getRune(getDayOfYear(date));
    }

    private Rune getRune(int dayOfYear) {
        return futhark[DAY_ORD[dayOfYear % 7]];
    }

    /**
     * Get the day of the year allowing for no leap-day
     *
     * @param date the date to find
     * @return date.getDayOfYear(), -1 if a leap year and month after Feb
     */
    protected int getDayOfYear(LocalDate date) {

        boolean isLeapYear = date.isLeapYear();
        boolean beforeMarch = date.getMonthValue() < Month.MARCH.getValue();

        int adjuster = !isLeapYear || beforeMarch ? 0 : 1;

        return date.getDayOfYear()-adjuster;

    }

    /**
     * Get the runes for the days of a month
     * @param month the month
     * @return List of the runes for that month
     */
    public List<Rune> getDayRunesForMonth(Month month) {

        List<Rune> runes = new ArrayList<>();

        // Always the same, so just use our base year
        LocalDate firstOfMonth = LocalDate.of(2014, month, 1);

        int monthLength = firstOfMonth.getMonth().length(false);

        int startDay = getDayOfYear(firstOfMonth);
        int endDay = startDay + monthLength;

        for (int day = startDay; day < endDay ; day++) {
            runes.add(getRune(day));
        }

        return runes;
    }
}
