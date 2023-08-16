package calendar.futharks;

public enum OldEnglish implements Rune {

    FEOH("Wealth", 0x16A0),
    UR("Aurochs", 0x16A2),
    THORN("Thorn", 0x16A6),
    OS("God", 0x16A9),
    RAD("Riding", 0x16B1),
    CEN("Torch", 0x16B3),
    GYFU("Gift", 0x16B7),
    WEN("Happiness", 0x16B9),
    HAEGL("Hail", 0x16BB),
    NYD("Need", 0x16BE),
    IS("Ice", 0x16C1),
    GER("Harvest", 0x16C4),
    EOH("Yew", 0x16C7),
    PEORD("Gaming", 0x16C8),
    EOLHX("Elk", 0x16C9),
    SIGEL("Sun", 0x16CB),
    TIR("Tiw", 0x16CF),
    BEORC("Birch", 0x16D2),
    EH("Horse", 0x16D6);

    private final String meaning;
    private final Character character;

    private OldEnglish(String meaning, int unicode) {
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
        for (Rune rune: OldEnglish.values() ) {
            System.out.printf("%s\n", rune.toString());
        }
    }

    public static void main(String[] args) {
        OldEnglish.display();
    }
}
