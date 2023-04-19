package edu.jsu.mcis.cs310.tas_sp23;

/**
* <p>This enum is used to define the PunchAdjustmentType constants.</p>
*/
public enum PunchAdjustmentType {

    NONE("None"),
    SHIFT_START("Shift Start"),
    SHIFT_STOP("Shift Stop"),
    SHIFT_DOCK("Shift Dock"),
    LUNCH_START("Lunch Start"),
    LUNCH_STOP("Lunch Stop"),
    INTERVAL_ROUND("Interval Round");

    private final String description;

    private PunchAdjustmentType(String d) {
        description = d;
    }
    
    /**
    * <p>This method is used to print the description variable as a string.</p>
    * @return description
    */
    @Override
    public String toString() {
        return description;
    }

}
