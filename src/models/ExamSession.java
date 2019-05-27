package models;

import java.util.Date;

public class ExamSession {
    private Date date;
    private TimePeriod timePeriod;
    private String place;

    public Date getDate() {
        return date;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public boolean hasConflict(ExamSession session) {
        return date.equals(session.getDate()) && timePeriod.hasOverlap(session.getTimePeriod());
    }
}
