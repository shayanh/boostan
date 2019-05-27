package models;

import java.time.DayOfWeek;

public class Session {
    private DayOfWeek dayOfWeek;
    private TimePeriod timePeriod;
    private String place;

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean hasConflict(Session session) {
        return dayOfWeek == session.getDayOfWeek() && timePeriod.hasOverlap(session.getTimePeriod());
    }
}
