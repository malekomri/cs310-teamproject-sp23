package edu.jsu.mcis.cs310.tas_sp23;

public enum EmployeeType {
    PART_TIME("Temporary / Part-Time"),
    FULL_TIME("Full-Time");

    private final String description;
    
    private EmployeeType(String d) {
        description = d;
    }

    @Override
    public String toString() {
        return description;
    }

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
