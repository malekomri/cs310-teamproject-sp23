package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;

public class Punch {
    
    //Instances
    private final Integer terminalid;
    private final Badge badge;
    private final EventType punchtype;
    private final Integer id;
    private final LocalDateTime originaltimestamp;
    private final LocalDateTime adjustedtimestamp;
    
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
    
    
    public Integer getTerminalid() {
        return terminalid;
    }
    
    public Badge badge() {
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
    

    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append(punchtype).append(": ");
        s.append(originaltimestamp);

        return s.toString();
    }
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
