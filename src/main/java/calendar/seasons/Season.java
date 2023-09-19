package calendar.seasons;

import java.time.Month;

public interface Season {

    Month start();
    Month end();
    String seasonName();
    String symbol();

}
