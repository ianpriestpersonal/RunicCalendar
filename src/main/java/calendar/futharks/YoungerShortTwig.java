package calendar.futharks;

public enum YoungerShortTwig implements Rune {

    FEOH("Wealth", 0x16A0),
    UR("Aurochs", 0x16A2),
    THORN("Thorn", 0x16A6),
    OSS("God", 0x16AD),
    RAD("Riding", 0x16B1),
    KAUN("Ulcer", 0x16B4),
    HAGALL("Hail", 0x16BD),
    NAUD("Need", 0x16BF),
    IS("Ice", 0x16C1),
    AR("Plenty", 0x16C6),
    SIGEL("Sun", 0x16CC),
    TIR("Tiw", 0x16D0),
    BEORC("Birch", 0x16D3),
    MADR("Human", 0x16D9),
    LOGR("Sea", 0x16DA),
    YR("Yew", 0x16E7),
    ARLAUG("Goden number 17", 0x16EE),
    TVIMADUR("Golden number 18", 0x16EF),
    BELGTHOR("Golden number 19", 0x16F0);

    private final String meaning;
    private final Character character;

    private YoungerShortTwig(String meaning, int unicode) {
        this.meaning = meaning;
        this.character = Character.valueOf((char) unicode);
    }

    public int magicNumber() {
        return ordinal() + 1;
    }

    public String meaning() {
        return meaning;
    }

    public String symbol() {
        return character.toString();
    }

    public Character character() {
        return character;
    }

    @Override
    public String toString() {
        return "English." + name() + "{" +
                "value='" + magicNumber() + '\'' +
                "meaning='" + meaning + '\'' +
                "character='" + character + '\'' +
                '}';
    }

    public static void display() {
        for (Rune rune: YoungerShortTwig.values() ) {
            System.out.printf("%s\n", rune.toString());
        }
    }

    public static void main(String[] args) {
        YoungerShortTwig.display();
    }
}
