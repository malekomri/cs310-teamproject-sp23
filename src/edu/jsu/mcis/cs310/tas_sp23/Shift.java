package edu.jsu.mcis.cs310.tas_sp23;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
* <p>Model class for creating and handling Shift objects.</p>
* @author Malek Omri, Drake Johnson
*/
public class Shift {
    
    private final int id;
    private final String description, shiftStart, shiftStop, roundInterval;
    private final String gracePeriod, dockPenalty, lunchStart, lunchStop, lunchThreshold;
    private long shiftDuration, lunchDuration;
    
    /**
    * <p>Constructor for creating a Shift object.</p>
    * @author Malek Omri, Drake Johnson
    * @param id
    * @param description
    * @param shiftStart
    * @param shiftStop
    * @param roundInterval
    * @param gracePeriod
    * @param dockPenalty
    * @param lunchStart
    * @param lunchStop
    * @param lunchThreshold
    */
    public Shift(int id, String description, String shiftStart, String shiftStop, String roundInterval, String gracePeriod, String dockPenalty, String lunchStart, String lunchStop, String lunchThreshold ) {

        this.id = id;
        this.description = description;
        this.shiftStart = shiftStart;
        this.shiftStop = shiftStop;
        this.roundInterval = roundInterval;
        this.gracePeriod = gracePeriod;
        this.dockPenalty = dockPenalty;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.lunchThreshold = lunchThreshold;

    }

    /**
    * <p>This method is used to get the id variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return id
    */
    public int getId() {
       return id;
    }
    
    /**
    * <p>This method is used to get the description variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return description
    */
    public String getDescription(){
        return description;
    }
    
    /**
    * <p>This method is used to get the shiftStart variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return shiftStart
    */
    public String getShiftStart(){
        return shiftStart;
    }
    
    /**
    * <p>This method is used to get the shiftStop variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return shiftStop
    */
    public String getShiftStop(){
        return shiftStop;
    }
    
    /**
    * <p>This method is used to get the roundInterval variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return roundInterval
    */
    public String getRoundInterval() {
        return roundInterval;
    }
    
    /**
    * <p>This method is used to get the gracePeriod variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return gracePeriod
    */
    public String getGracePeriod() {
        return gracePeriod;
    }
    
    /**
    * <p>This method is used to get the dockPenalty variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return dockPenalty
    */
    public String getDockPenalty() {
        return dockPenalty;
    }
    
    /**
    * <p>This method is used to get the lunchStart variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return lunchStart
    */
    public String getLunchStart() {
        return lunchStart;
    }
    
    /**
    * <p>This method is used to get the lunchStop variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return lunchStop
    */
    public String getLunchStop() {
        return lunchStop;
    }
    
    /**
    * <p>This method is used to get the lunchThreshold variable.</p>
    * @author Malek Omri, Drake Johnson
    * @return lunchThreshold
    */
    public String getLunchThreshold() {
        return lunchThreshold;
    }
    
    /**
    * <p>This method is used to set the shiftDuration variable.</p>
    * @author Malek Omri, Drake Johnson
    * @param shiftStart
    * @param shiftStop
    * @return shiftDuration
    * @throws ParseException
    */
    public long setShiftDuration(String shiftStart, String shiftStop) throws ParseException {
        shiftDuration = Duration.between(LocalTime.parse(shiftStart), LocalTime.parse(shiftStop)).toMinutes();
        return shiftDuration;
    }
    
    /**
    * <p>This method is used to set the lunchDuration variable.</p>
    * @author Malek Omri, Drake Johnson
    * @param lunchStart
    * @param lunchStop
    * @return lunchDuration
    * @throws ParseException
    */
    public long setLunchDuration(String lunchStart, String lunchStop) throws ParseException {
        lunchDuration = Duration.between(LocalTime.parse(lunchStart), LocalTime.parse(lunchStop)).toMinutes();
        return lunchDuration;
    }
    
    /**
    * <p>This method is used to format the information contained in a Shift object as a string.</p>
    * @author Malek Omri, Drake Johnson
    * @return The formatted string
    */
    @Override
    public String toString(){
    try {
        setShiftDuration(shiftStart, shiftStop);
        setLunchDuration(lunchStart, lunchStop);
    } catch (ParseException ex) {
        Logger.getLogger(Shift.class.getName()).log(Level.SEVERE, null, ex);
    }
        StringBuilder s = new StringBuilder();
        
        s.append(description).append(": ");
        s.append(shiftStart.substring(0, 5)).append(" - ");
        s.append(shiftStop.substring(0, 5)).append(" (");
        s.append(shiftDuration).append(" minutes); ");
        s.append("Lunch: ").append(lunchStart.substring(0, 5)).append(" - ");
        s.append(lunchStop.substring(0, 5)).append(" (");
        s.append(lunchDuration).append(" minutes)");
        
        return s.toString();
    }
}