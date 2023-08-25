package calendar.symbols;

import calendar.futharks.Rune;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.TemporalAdjusters.firstInMonth;

/**
 * Note that leap years have two symbols, since there is no allowance for a leap day
 *
 * For leap year, the sunday symbol moves back one (i.e. -1) after the leap day on 25th Feb (in this calendar)
 */
public class SundaySymbol {

    private final Rune[] futhark;
    private final DaySymbol daySymbol;

    public SundaySymbol(Rune[] futhark) {

        this.futhark = futhark;
        this.daySymbol = new DaySymbol(futhark);
    }

    public Rune getSundayRune(LocalDate date) {
        return getSundayRune(date.getMonth(), date.getYear());
    }

    /**
     * Get the sunday rune for a specific month.
     *
     * If not a leap year it'll be the same all year. For a leap year it'll change from Feb to March
     *
     * @param month the month 1-12
     * @param year the year
     * @return the rune that means sunday
     */
    protected Rune getSundayRune(Month month, int year) {

        LocalDate yearMonth = LocalDate.of(year, month, 1);
        return daySymbol.getRune(yearMonth.with(firstInMonth(DayOfWeek.SUNDAY)));
    }

    /**
     * The rune (or runes if a leap year) that represent sunday for a given year
     *
     * @param year the year
     * @return one or two len list of runes
     */
    public List<Rune> getSundayRunes(int year) {

        List<Rune> runes = new ArrayList<>();

        runes.add(getSundayRune(Month.JANUARY, year));
        if ( Year.isLeap(year) )
            runes.add(getSundayRune(Month.MARCH, year));

        return runes;
    }
}
