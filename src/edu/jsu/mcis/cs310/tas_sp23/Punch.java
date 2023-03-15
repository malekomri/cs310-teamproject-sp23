package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
