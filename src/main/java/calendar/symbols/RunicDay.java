package calendar.symbols;

import calendar.futharks.Rune;

import java.util.Optional;

public class RunicDay {

    Optional<Rune> newMoon;
    Rune day;

    MoonPhase moonPhase;

    public RunicDay(Optional<Rune> newMoon, Rune day, MoonPhase moonPhase) {
        this.newMoon = newMoon;
        this.day = day;
        this.moonPhase = moonPhase;
    }

    public Optional<Rune> getNewMoon() {
        return newMoon;
    }

    public Rune getDay() {
        return day;
    }

    public MoonPhase getMoonPhase() {
        return moonPhase;
    }

    @Override
    public String toString() {
        return "RunicDay{" +
                "newMoon=" + newMoon +
                ", day=" + day +
                ", moonPhase=" + moonPhase +
                '}';
    }
}
