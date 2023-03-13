package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Punch {
    
    //Instances
    private final Integer terminalid;
    private final Badge badge;
    private final EventType punchtype;
    private final Integer id;
    private final LocalDateTime originaltimestamp;
    private final LocalDateTime adjustedtimestamp;
    
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    
    public PunchAdjustmentType adjustmenttype;
    
    
    //New Punch Constructor
    public Punch(int terminalid, Badge badge, EventType punchtype){
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.id = null;
        originaltimestamp = null;
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
    
    public Badge badge() {
        return badge;
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
    
    //toString Methods
    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ");
        s.append(originaltimestamp.format(formatter).toUpperCase());

        return s.toString();
    }
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
