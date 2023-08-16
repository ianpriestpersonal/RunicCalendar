package calendar.play;

import calendar.futharks.Rune;

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

    public List<Rune> getDays(Month month) {
        return daySymbol.getDayRunesForMonth(month);
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

}
