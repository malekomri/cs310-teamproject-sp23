package edu.jsu.mcis.cs310.tas_sp23;

import java.time.DayOfWeek;
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

    public boolean isWeekend() {
        return (originaltimestamp.getDayOfWeek() == DayOfWeek.SATURDAY || originaltimestamp.getDayOfWeek() == DayOfWeek.SUNDAY);
    }
    
    public void adjust(Shift s) {
        LocalDateTime adjustedTime = this.originaltimestamp;
        PunchAdjustmentType adjustmentType = PunchAdjustmentType.NONE;
    
        // Shift start adjustment
        LocalTime shiftStart = LocalTime.parse(s.getShiftStart());
        LocalTime shiftStartInterval = shiftStart.minusMinutes(Integer.parseInt(s.getRoundInterval()));
        if (this.originaltimestamp.toLocalTime().isBefore(shiftStartInterval)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStart);
            adjustmentType = PunchAdjustmentType.SHIFT_START;
        }
    
        // Shift stop adjustment
        LocalTime shiftStop = LocalTime.parse(s.getShiftStop());
        LocalTime shiftStopInterval = shiftStop.plusMinutes(Integer.parseInt(s.getRoundInterval()));
        if (this.originaltimestamp.toLocalTime().isAfter(shiftStopInterval)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStop);
            adjustmentType = PunchAdjustmentType.SHIFT_STOP;
        }
    
        // Lunch start adjustment
        LocalTime lunchStart = LocalTime.parse(s.getLunchStart());
        if (this.originaltimestamp.toLocalTime().isAfter(shiftStart) && this.originaltimestamp.toLocalTime().isBefore(lunchStart)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), lunchStart);
            adjustmentType = PunchAdjustmentType.LUNCH_START;
        }
    
        // Lunch stop adjustment
        LocalTime lunchStop = LocalTime.parse(s.getLunchStop());
        if (this.originaltimestamp.toLocalTime().isAfter(lunchStop) && this.originaltimestamp.toLocalTime().isBefore(shiftStop)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), lunchStop);
            adjustmentType = PunchAdjustmentType.LUNCH_STOP;
        }
    
        // Grace period adjustment
        LocalTime shiftStartGrace = shiftStart.plusMinutes(Integer.parseInt(s.getGracePeriod()));
        LocalTime shiftStopGrace = shiftStop.minusMinutes(Integer.parseInt(s.getGracePeriod()));
        if (this.originaltimestamp.toLocalTime().isAfter(shiftStartGrace) && this.originaltimestamp.toLocalTime().isBefore(shiftStart)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStart);
            adjustmentType = PunchAdjustmentType.GRACE_PERIOD;
        } else if (this.originaltimestamp.toLocalTime().isAfter(shiftStop) && this.originaltimestamp.toLocalTime().isBefore(shiftStopGrace)) {
            adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStop);
            adjustmentType = PunchAdjustmentType.GRACE_PERIOD;
        }
    
       // Dock penalty adjustment
LocalTime shiftStartDock = shiftStart.plusMinutes(Integer.parseInt(s.getGracePeriod())).plusMinutes(Integer.parseInt(s.getDockPenalty()));
LocalTime shiftStopDock = shiftStop.minusMinutes(Integer.parseInt(s.getGracePeriod())).minusMinutes(Integer.parseInt(s.getDockPenalty()));
if (this.originaltimestamp.toLocalTime().isAfter(shiftStartGrace) && this.originaltimestamp.toLocalTime().isBefore(shiftStartDock)) {
    adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStartDock);
    adjustmentType = PunchAdjustmentType.SHIFT_DOCK;
} else if (this.originaltimestamp.toLocalTime().isAfter(shiftStopDock) && this.originaltimestamp.toLocalTime().isBefore(shiftStopGrace)) {
    adjustedTime = LocalDateTime.of(this.originaltimestamp.toLocalDate(), shiftStopDock);
    adjustmentType = PunchAdjustmentType.SHIFT_DOCK;
}

// Interval rounding adjustment
if (!s.isWeekend(this.originaltimestamp.getDayOfWeek().getValue())) {
    long shiftStartMinutes = s.getShiftStartMinutes();
    long punchMinutes = adjustedTime.toLocalTime().toSecondOfDay() / 60;
    long difference = punchMinutes - shiftStartMinutes;
    long remainder = difference % s.getRoundIntervalMinutes();
    long roundedMinutes = difference - remainder;
    if (remainder >= s.getRoundIntervalMinutes() / 2) {
        roundedMinutes += s.getRoundIntervalMinutes();
    }
    long totalMinutes = shiftStartMinutes + roundedMinutes;
    LocalTime roundedTime = LocalTime.of((int)(totalMinutes / 60), (int)(totalMinutes % 60), 0);
    adjustedtimestamp = LocalDateTime.of(adjustedTime.toLocalDate(), roundedTime);
    adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
} else {
    adjustedtimestamp = adjustedTime;
}

// Save adjusted timestamp and adjustment type
this.adjustedtimestamp = adjustedtimestamp;
this.adjustmenttype = adjustmentType;
}


public String printAdjusted() {
    StringBuilder s = new StringBuilder();
    s.append('#').append(badge.getId()).append(' ');
    s.append(punchtype.toString()).append(": ");
    s.append(adjustedtimestamp.format(formatter).toUpperCase());
    s.append(" (").append(adjustmenttype.toString()).append(')');
    return s.toString();
}

    @Override
    public String toString() {
        return printOriginal();
    }
}
