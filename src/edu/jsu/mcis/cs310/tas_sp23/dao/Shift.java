package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.time.LocalTime;
import java.util.Map;

public class Shift {
    
    private int id;
    private String description;
    private LocalTime start;
    private LocalTime stop;
    private int interval;
    private int gracePeriod;
    private int dock;
    private LocalTime lunchStart;
    private LocalTime lunchStop;
    private int lunchDeduct;
    private int shiftDuration;
    private int lunchDuration;
    
    public Shift(Map<String, String> params) {
        this.id = Integer.parseInt(params.get("id"));
        this.description = params.get("description");
        this.start = LocalTime.parse(params.get("start"));
        this.stop = LocalTime.parse(params.get("stop"));
        this.interval = Integer.parseInt(params.get("interval"));
        this.gracePeriod = Integer.parseInt(params.get("graceperiod"));
        this.dock = Integer.parseInt(params.get("dock"));
        this.lunchStart = LocalTime.parse(params.get("lunchstart"));
        this.lunchStop = LocalTime.parse(params.get("lunchstop"));
        this.lunchDeduct = Integer.parseInt(params.get("lunchdeduct"));
        this.shiftDuration = Integer.parseInt(params.get("shiftduration"));
        this.lunchDuration = Integer.parseInt(params.get("lunchduration"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(int gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public void setLunchStart(LocalTime lunchStart) {
        this.lunchStart = lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public void setLunchStop(LocalTime lunchStop) {
        this.lunchStop = lunchStop;
    }

    public int getLunchDeduct() {
        return lunchDeduct;
    }

    public void setLunchDeduct(int lunchDeduct) {
        this.lunchDeduct = lunchDeduct;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    public void setLunchDuration(int lunchDuration) {
        this.lunchDuration = lunchDuration;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(int shiftDuration) {
        this.shiftDuration = shiftDuration;
    }
}
