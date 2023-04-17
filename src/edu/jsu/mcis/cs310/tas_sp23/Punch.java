package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* <p>Model class for creating and handling Punch objects.</p>
* @author Oakley Pate, Martell Norman
*/
public class Punch {
    
    //Instance Variables
    
    /**
    * <p>Instance variable to represent the terminalid from the database.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  Integer terminalid;
    
    /**
    * <p>Instance variable to represent a Badge object.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  Badge badge;
    
    /**
    * <p>Instance variable to represent the EventType enum.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  EventType punchtype;
    
    /**
    * <p>Instance variable to represent the id from the database.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  Integer id;
    
    /**
    * <p>Instance variable to represent the timestamp before alterations as listed in the database.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  LocalDateTime originaltimestamp;
    
    /**
    * <p>Instance variable to represent the timestamp after alterations.</p>
    * @author Oakley Pate, Martell Norman
    */
    private  LocalDateTime adjustedtimestamp;
    
    /**
    * <p>Final used to format DateTime objects.</p>
    * @author Oakley Pate, Martell Norman
    */
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
    
    /**
    * <p>Instance variable for adjustment.</p>
    * @author Oakley Pate, Martell Norman
    */
    public PunchAdjustmentType adjustmenttype;
    
    
    //Constructors
    
    /**
    * <p>Constructor for creating a new Punch object.</p>
    * @author Oakley Pate, Martell Norman
    * @param terminalid
    * @param badge
    * @param punchtype
    */
    public Punch(int terminalid, Badge badge, EventType punchtype){
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.id = null;
        originaltimestamp = LocalDateTime.now();
        adjustedtimestamp = null;
        
    }
    
    /**
    * <p>Constructor for an existing Punch object.</p>
    * @author Oakley Pate, Martell Norman
    * @param id
    * @param terminalid
    * @param badge
    * @param originaltimestamp
    * @param punchtype
    */
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchtype = punchtype;
        this.id = id;
        this.originaltimestamp = originaltimestamp;
        this.adjustedtimestamp = null;
        
    }
    
    
    //Getter Methods
    
    /**
    * <p>This method is used to get the terminalid variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the terminalid
    */
    public Integer getTerminalid() {
        return terminalid;
    }
    
    /**
    * <p>This method is used to get the badge variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the badge
    */
    public Badge getBadge() {
        return badge;
    }
    
    /**
    * <p>This method is used to get the id variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the id
    */
    public Integer getId() {
        return id;
    }
    
    /**
    * <p>This method is used to get the punchtype variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the punchtype
    */
    public EventType getPunchtype() {
        return punchtype;
    }
    
    /**
    * <p>This method is used to get the originaltimestamp variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the originaltimestamp
    */
    public LocalDateTime getOriginaltimestamp() {
        return originaltimestamp;
    }
    
    /**
    * <p>This method is used to get the adjustedtimestamp variable.</p>
    * @author Oakley Pate, Martell Norman
    * @return the adjustedtimestamp
    */
    public LocalDateTime getAdjustedtimestamp() {
        return adjustedtimestamp;
    }
    
    
    //Setters
    
    /**
    * <p>This method is used to set the terminalid variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param terminalid
    */
    public void setTerminalid(Integer terminalid) {
        this.terminalid = terminalid;
    }
    
    /**
    * <p>This method is used to set the badge variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param badge
    */
    public void setBadge(Badge badge) {
        this.badge = badge;
    }
    
    /**
    * <p>This method is used to set the punchtype variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param punchtype
    */
    public void setPunchtype(EventType punchtype) {
        this.punchtype = punchtype;
    }
    
    /**
    * <p>This method is used to set the id variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param id
    */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
    * <p>This method is used to set the originaltimestamp variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param originaltimestamp
    */
    public void setOriginaltimestamp(LocalDateTime originaltimestamp) {
        this.originaltimestamp = originaltimestamp;
    }
    
    /**
    * <p>This method is used to set the adjustedtimestamp variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param adjustedtimestamp
    */
    public void setAdjustedtimestamp(LocalDateTime adjustedtimestamp) {
        this.adjustedtimestamp = adjustedtimestamp;
    }
    
    /**
    * <p>This method is used to set the adjustmenttype variable.</p>
    * @author Oakley Pate, Martell Norman
    * @param adjustmenttype
    */
    public void setAdjustmenttype(PunchAdjustmentType adjustmenttype) {
        this.adjustmenttype = adjustmenttype;
    }

    
    //toString() methods
    
    /**
    * <p>This method is used to format the information contained in a Punch object as a string.</p>
    * @author Oakley Pate, Martell Norman
    * @return The formatted string
    */
    public String printOriginal() {
        
        StringBuilder s = new StringBuilder();

        s.append('#').append(badge.getId()).append(' ');
        s.append(punchtype).append(": ");
        s.append(originaltimestamp.format(formatter).toUpperCase());

        return s.toString();
    }
    
    /**
    * <p>This overrides the default toString() method and calls the {@link #printOriginal printOriginal()} method instead.</p>
    * @author Oakley Pate, Martell Norman
    * @return The string formatted by {@link #printOriginal printOriginal()}
    */
    @Override
    public String toString() {
        return printOriginal();
    }
}
