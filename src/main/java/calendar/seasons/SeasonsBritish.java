package calendar.seasons;

import java.time.Month;

public enum SeasonsBritish implements Season {
    SPRING(Month.MARCH, Month.MAY, "Spring", ""),
    SUMMER(Month.JUNE, Month.AUGUST, "Summer", ""),
    AUTUMN(Month.SEPTEMBER, Month.NOVEMBER, "Autumn", ""),
    WINTER(Month.DECEMBER, Month.FEBRUARY, "Winter", ""),
    ;

    private Month start;
    private Month end;
    private String seasonName;
    private String symbol;

    private SeasonsBritish(Month start, Month end, String seasonName, String symbol) {
        this.start = start;
        this.end = end;
        this.seasonName = seasonName;
        this.symbol = symbol;
    }

    @Override
    public Month start() {
        return start;
    }

    @Override
    public Month end() {
        return end;
    }

    @Override
    public String seasonName() {
        return seasonName;
    }

    @Override
    public String symbol() {
        return symbol;
    }
}
