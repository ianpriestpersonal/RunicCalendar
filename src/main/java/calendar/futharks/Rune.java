package calendar.futharks;

public interface Rune {
    /**
     *
     * @return int The golden-number value of the rune when used in a Runic Calendar
     */
    int magicNumber();

    /**
     *
     * @return A string giving a brief (generally one-word) meaning for the rune
     */
    String meaning();

    /**
     *
     * @return The rune's symbol as a String
     */
    String symbol();

    /**
     *
     * @return The rune's symbol as a Character
     */
    Character character();

}
