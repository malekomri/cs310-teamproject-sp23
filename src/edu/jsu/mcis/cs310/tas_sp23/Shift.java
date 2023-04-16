package edu.jsu.mcis.cs310.tas_sp23;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Shift {
 private final int id;
 private final String description, shiftStart, shiftStop, roundInterval;
 private final String gracePeriod, dockPenalty, lunchStart, lunchStop, lunchThreshold;
 private long shiftDuration, lunchDuration;
 
 public Shift(int id, String description, String shiftStart, String shiftStop, String roundInterval, String gracePeriod, String dockPenalty, String lunchStart, String lunchStop, String lunchThreshold ){
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
 public int getId(){
 return id;
 }
 public String getDescription(){
 return description;
 }
 public String getShiftStart(){
 return shiftStart;
 }
 public String getShiftStop(){
 return shiftStop;
 }
 public String getRoundInterval(){
 return roundInterval;
 }
 public String getGracePeriod(){
 return gracePeriod;
 }
 public String getDockPenalty(){
 return dockPenalty;
 }
 public String getLunchStart() {
 return lunchStart;
 }
 public String getLunchStop(){
 return lunchStop;
 }
 public String getLunchThreshold(){
 return lunchThreshold;
 }
 public long setShiftDuration(String shiftStart, String shiftStop) throws ParseException {
 shiftDuration = Duration.between(LocalTime.parse(shiftStart), LocalTime.parse(shiftStop)).toMinutes();
 return shiftDuration;
 }
 public long setLunchDuration(String lunchStart, String lunchStop) throws ParseException {
 lunchDuration = Duration.between(LocalTime.parse(lunchStart), LocalTime.parse(lunchStop)).toMinutes();
 return lunchDuration;
 }
 @Override
 public String toString(){
 try{
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

 public boolean isWeekend(int dayOfWeek) {
    return (dayOfWeek == DayOfWeek.SATURDAY.getValue() || dayOfWeek == DayOfWeek.SUNDAY.getValue());
}

public long getShiftStartMinutes() {
    return Long.parseLong(shiftStart.substring(0, 2)) * 60 + Long.parseLong(shiftStart.substring(3, 5));
}

public int getRoundIntervalMinutes() {
    return Integer.parseInt(roundInterval);
}


}

