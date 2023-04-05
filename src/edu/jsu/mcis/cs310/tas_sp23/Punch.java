package edu.jsu.mcis.cs310.tas_sp23;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Punch {
    
    //Instances
    private  Integer terminalid;
    private  Badge badge;
    private  EventType punchtype;
    private  Integer id;
    private  LocalDateTime originaltimestamp;
    private  LocalDateTime adjustedtimestamp;

  
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    
    public PunchAdjustmentType adjustmenttype;
    
    
    //New Punch Constructor
    public Punch(int terminalid, Badge badge, EventType punchtype){
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.id = null;
        originaltimestamp = LocalDateTime.now();
        adjustedtimestamp = null;
        
    }
    
    //Existing Punch Constructor
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.id = id;
        this.originaltimestamp = originaltimestamp;
        this.adjustedtimestamp = null;
        
    }
    
    //Getter Methods
    public Integer getTerminalid() {
        return terminalid;
    }
    

    public Badge getBadge() {
        return badge;
    }
    
    
    public Integer getId() {
        return id;
    }
    
    public EventType getPunchtype() {
        return punchtype;
    }
    
    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }
    
    public LocalDateTime getAdjustedtimestamp() {
        return adjustedtimestamp;
    }
    
    //Setters

    public void setTerminalid(Integer terminalid) {
        this.terminalid = terminalid;
    }
    
    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    public void setPunchtype(EventType punchtype) {
        this.punchtype = punchtype;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setOriginaltimestamp(LocalDateTime originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }
    
    public void setAdjustedtimestamp(LocalDateTime adjustedtimestamp) {
        this.adjustedtimestamp = adjustedtimestamp;
    }
    
    public void setAdjustmenttype(PunchAdjustmentType adjustmenttype) {
        this.adjustmenttype = adjustmenttype;
    }

    //toString Methods
    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ");
        s.append(originaltimestamp.format(formatter).toUpperCase());

        return s.toString();
    }

      public void adjust(Shift s) {
        adjustedtimestamp = originaltimestamp;

        if (punchtype == EventType.TIME_OUT) {
            adjustmenttype = PunchAdjustmentType.NONE;
            return;
        }
        LocalTime shiftStartTime = LocalTime.parse(s.getShiftStart());
        LocalTime shiftEndTime = LocalTime.parse(s.getShiftStop());
        LocalTime lunchStartTime = LocalTime.parse(s.getLunchStart());
        LocalTime lunchEndTime = LocalTime.parse(s.getLunchStop());
        
        LocalDateTime shiftStart = originaltimestamp.withHour(shiftStartTime.getHour()).withMinute(shiftStartTime.getMinute());
        LocalDateTime shiftEnd = originaltimestamp.withHour(shiftEndTime.getHour()).withMinute(shiftEndTime.getMinute());
        LocalDateTime lunchStart = originaltimestamp.withHour(lunchStartTime.getHour()).withMinute(lunchStartTime.getMinute());
        LocalDateTime lunchEnd = originaltimestamp.withHour(lunchEndTime.getHour()).withMinute(lunchEndTime.getMinute());
        
        int roundInterval = Integer.parseInt(s.getRoundInterval());
        int gracePeriod = Integer.parseInt(s.getGracePeriod());
        int dock = Integer.parseInt(s.getDockPenalty());

        boolean isWeekend = originaltimestamp.getDayOfWeek().getValue() >= 6;

        if (!isWeekend) {
            Duration startDifference = Duration.between(shiftStart, originaltimestamp);
            Duration endDifference = Duration.between(originaltimestamp, shiftEnd);

            if (startDifference.toMinutes() < roundInterval && startDifference.toMinutes() >= 0) {
                adjustedtimestamp = shiftStart;
                adjustmenttype = PunchAdjustmentType.SHIFT_START;
            } else if (endDifference.toMinutes() < roundInterval && endDifference.toMinutes() >= 0) {
                adjustedtimestamp = shiftEnd;
                adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
            } else if (startDifference.toMinutes() <= gracePeriod && startDifference.toMinutes() > 0) {
                adjustedtimestamp = shiftStart;
                adjustmenttype = PunchAdjustmentType.GRACE;
            } else if (endDifference.toMinutes() <= gracePeriod && endDifference.toMinutes() > 0) {
                adjustedtimestamp = shiftEnd;
                adjustmenttype = PunchAdjustmentType.GRACE;
            } else if (startDifference.toMinutes() <= dock && startDifference.toMinutes() > gracePeriod) {
                adjustedtimestamp = shiftStart.plusMinutes(dock);
                adjustmenttype = PunchAdjustmentType.DOCK;
            } else if (endDifference.toMinutes() <= dock && endDifference.toMinutes() > gracePeriod) {
                adjustedtimestamp = shiftEnd.minusMinutes(dock);
                adjustmenttype = PunchAdjustmentType.DOCK;
            }
        }

        if (adjustmenttype == null) {
            long modInterval = originaltimestamp.toLocalTime().toSecondOfDay() % (roundInterval * 60);
            if (modInterval < roundInterval * 30) {
                adjustedtimestamp = adjustedtimestamp.minus(modInterval, ChronoUnit.SECONDS);
            } else {
                adjustedtimestamp = adjustedtimestamp.plus(roundInterval * 60 - modInterval, ChronoUnit.SECONDS);
            }
            adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
        }

        adjustedtimestamp = adjustedtimestamp.truncatedTo(ChronoUnit.MINUTES);
    }


    public String printAdjusted() {
        
    
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        String badgeIdString = (badge != null) ? badge.getId() : "UNKNOWN";
        Date adjustedDate = new Date(adjustedtimestamp.getTime()); // Convert Timestamp to Date
        return "#" + badgeIdString + " " + (punchtype == EventType.CLOCK_IN ? "CLOCK IN" : "CLOCK OUT") + ": " + dateFormat.format(adjustedDate) + " (" + adjustmenttype.toString() + ")";
    }
    
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
