package calendar.seasons;

import java.time.Month;

public enum OldMonthsBritish implements OldMonth {

    JANUARY(Month.JANUARY, "Æftera Geola", "After Yule, Midwinter Month", ""),
    FEBRUARY(Month.FEBRUARY, "Sōlmōnaþ", "Mud Month, Flat Cakes Month", ""),
    MARCH(Month.MARCH, "Hrēðmonaþ", "Month of godess Hreða", ""),
    APRIL(Month.APRIL, "Eostremonaþ", "Month of godess Eostre, Grass Month", ""),
    MAY(Month.MAY, "Ðrimilce-monaþ", "Three Milkings Month, Leaf Month", ""),
    JUNE(Month.JUNE, "Ærra liða", "Before Mild, Midsummer Month ", ""),
    JULY(Month.JULY, "Æftera liða", "After Mild, Hay Month", ""),
    AUGUST(Month.AUGUST, "Weodmonaþ", "Weed or Plant Month, Harvest Month", ""),
    SEPTEMBER(Month.SEPTEMBER, "Hāligmonaþ", "Holy Month", ""),
    OCTOBER(Month.OCTOBER, "Winterfylleþ", "Winter Full Moon", ""),
    NOVEMBER(Month.NOVEMBER, "Blot-monaþ", "Blood Month, Frost Month", ""),
    DECEMBER(Month.DECEMBER, "Ærra Geola", "Before Yule", ""),
    ;

    private final Month modernMonth;
    private final String oldName;
    private final String oldMeaning;
    private final String symbol;

    private OldMonthsBritish(Month modernMonth, String oldName, String oldMeaning, String symbol) {
        this.modernMonth = modernMonth;
        this.oldName = oldName;
        this.oldMeaning = oldMeaning;
        this.symbol = symbol;
    }

    @Override
    public Month modernMonth() {
        return modernMonth;
    }

    @Override
    public String oldName() {
        return oldName;
    }

    @Override
    public String oldMeaning() {
        return oldMeaning;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
