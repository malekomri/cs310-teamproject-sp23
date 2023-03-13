package edu.jsu.mcis.cs310.tas_sp23;



public class Department {
    private int departmentid, terminalid;
    private String description;

    public Department(int departmentid, int terminalid, String description) {
        this.departmentid = departmentid;
        this.terminalid = terminalid;
        this.description = description;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("#%d (%s), Terminal ID: %d", departmentid, description, terminalid);
    }
}
