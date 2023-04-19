package edu.jsu.mcis.cs310.tas_sp23;

/**
* <p>This enum is used to define the CLOCK_OUT, CLOCK_IN, and TIME_OUT constants.</p>
*/
public enum EventType {

    CLOCK_OUT("CLOCK OUT"),
    CLOCK_IN("CLOCK IN"),
    TIME_OUT("TIME OUT");

    private final String description;

    private EventType(String d) {
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
