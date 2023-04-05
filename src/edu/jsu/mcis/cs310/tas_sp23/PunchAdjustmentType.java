package edu.jsu.mcis.cs310.tas_sp23;

public enum PunchAdjustmentType {

    NONE("None"),
    SHIFT_START("Shift Start"),
    SHIFT_STOP("Shift Stop"),
    SHIFT_DOCK("Shift Dock"),
    LUNCH_START("Lunch Start"),
    LUNCH_STOP("Lunch Stop"),
    INTERVAL_ROUND("Interval Round"),
    GRACE("Grace Period"),
    DOCK("Dock");

    private final String description;

    private PunchAdjustmentType(String d) {
        description = d;
    }

    @Override
    public String toString() {
        return description;
    }

}
