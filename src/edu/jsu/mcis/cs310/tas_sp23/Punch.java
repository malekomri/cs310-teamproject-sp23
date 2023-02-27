package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;

public class Punch {
    
    //Check Instance Fields (correct type and initialized to correct value)
    //Check if constructors are 
    
    //Instances
    private Integer terminalid;
    private Badge badge;
    private EventType punchtype;
    private Integer id = null;
    private LocalDateTime originaltimestamp;
    private LocalDateTime adjustedtimestamp = null;
    
    public PunchAdjustmentType adjustmenttype;
    
    //Existing Punch Constructor
    public Punch(int terminalid, Badge badge, EventType punchtype){
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        
        adjustedtimestamp = LocalDateTime.now();
    }
    
    //New Punch Constructor
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        
        this.originaltimestamp = LocalDateTime.now();
    }
    
    //Getter Methods (mostly done, check if all methods are needed or if more are needed)
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
    
    //toString() Methods (incomplete)
    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        //event type (clock out or clock in)
        //(day) date time

        return s.toString();
    }
    
    @Override
    public String toString() {
        return printOriginal();
    }
}
