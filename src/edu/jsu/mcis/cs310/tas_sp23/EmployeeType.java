package edu.jsu.mcis.cs310.tas_sp23;

/**
* <p>This enum is used to define the PART_TIME and FULL_TIME constants.</p>
*/
public enum EmployeeType {
    PART_TIME("Temporary / Part-Time"),
    FULL_TIME("Full-Time");

    private final String description;
    
    private EmployeeType(String d) {
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
    
    /**
    * <p>This method is used to get the Employee type (part-time or full-time) from the id.</p>
    * @param id
    * @return EmployeeType
    */
    public static EmployeeType getTypeById(int id) {
        switch (id) {
            case 0:
                return PART_TIME;
            case 1:
                return FULL_TIME;
            default:
                throw new IllegalArgumentException("Invalid EmployeeType id: " + id);
        }
    }
}
