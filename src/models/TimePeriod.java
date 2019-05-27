package models;

import java.sql.Time;

public class TimePeriod {
    Time start;
    Time finish;

    boolean hasOverlap(TimePeriod timePeriod) {
        if (start.before(timePeriod.finish) && start.after(timePeriod.start))
            return true;
        if (finish.before(timePeriod.finish) && finish.after(timePeriod.start))
            return true;
        return false;
    }
}
