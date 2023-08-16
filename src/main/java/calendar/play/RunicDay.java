package calendar.play;

import calendar.futharks.Rune;

import java.util.Optional;

public class RunicDay {

    Optional<Rune> newMoon;
    Rune day;

    public RunicDay(Optional<Rune> newMoon, Rune day) {
        this.newMoon = newMoon;
        this.day = day;
    }

    public Optional<Rune> getNewMoon() {
        return newMoon;
    }

    public Rune getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "RunicDay{" +
                "newMoon=" + newMoon +
                ", day=" + day +
                '}';
    }
}
