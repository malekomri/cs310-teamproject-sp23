package edu.jsu.mcis.cs310.tas_sp23;

public enum EmployeeType {

    PART_TIME("Part-Time"),
    FULL_TIME("Full-Time");
    private final String description;

    private EmployeeType(String d) {
        description = d;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
