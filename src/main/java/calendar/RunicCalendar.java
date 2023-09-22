package calendar;

import calendar.futharks.Rune;
import calendar.symbols.DaySymbol;
import calendar.symbols.MoonSymbol;
import calendar.symbols.RunicDay;
import calendar.symbols.SundaySymbol;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RunicCalendar {

    private final SundaySymbol sundaySymbol;
    private final DaySymbol daySymbol;
    private final MoonSymbol moonSymbol;
    private final int year;

    public RunicCalendar(int year, Rune[] futhark) {
        this.year = year;
        this.sundaySymbol = new SundaySymbol(futhark);
        this.daySymbol = new DaySymbol(futhark);
        this.moonSymbol = new MoonSymbol(futhark);
    }

    /**
     *
     * @return one, or two if leap year, sunday runes
     */
    public List<Rune> getSundayRunes() {
        return sundaySymbol.getSundayRunes(year);
    }

    public List<RunicDay> getCalendar(Month month) {

        List<RunicDay> calendar = new ArrayList<>();

        List<Rune> dayRunesForMonth = daySymbol.getDayRunesForMonth(month);
        List<Optional<Rune>> moonRhyme = moonSymbol.getMoonSymbolsForMonth(month, year);

        assert dayRunesForMonth.size() == moonRhyme.size();

        for (int i = 0; i < dayRunesForMonth.size(); i++) {
            calendar.add(new RunicDay(moonRhyme.get(i), dayRunesForMonth.get(i)));
        }

        return calendar;
    }

    /**
     * Get the current sunday rune.
     *
     * In a leap year, will return the second sunday rune if month is after feb
     * @param mmonth the month
     * @return the sunday rune
     */
    public Rune getSundayRune(Month mmonth) {
        return sundaySymbol.getSundayRune(mmonth, year);
    }

    /**
     * Get the moon rune for the year
     * @return the moon rune
     */
    public Rune getMoonRune() {
        return moonSymbol.getMoonRune(year);
    }
}