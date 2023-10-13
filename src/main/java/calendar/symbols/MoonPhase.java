package calendar.symbols;

public class MoonPhase {

    final double phase;
    final int ordinal;


    public MoonPhase(double phase, int ordinal) {
        this.phase = phase;
        this.ordinal = ordinal;
    }

    public double getPhase() {
        return phase;
    }

    public int getOrdinal() {
        return ordinal;
    }

    @Override
    public String toString() {
        return "MoonPhase{" +
                "phase=" + phase +
                ", ordinal=" + ordinal +
                '}';
    }
}
