package edu.jsu.mcis.cs310.tas_sp23;

/**
* <p>Model class for creating and handling Department objects.</p>
* @author Christopher Adkins
*/
public class Department {
    private int departmentid, terminalid;
    private String description;
    
    /**
    * <p>Constructor for creating a Department object.</p>
    * @author Christopher Adkins
    * @param departmentid
    * @param terminalid
    * @param description
    */
    public Department(int departmentid, int terminalid, String description) {
        this.departmentid = departmentid;
        this.terminalid = terminalid;
        this.description = description;
    }
    
    /**
    * <p>This method is used to get the departmentid variable.</p>
    * @author Christopher Adkins
    * @return departmentid
    */
    public int getDepartmentid() {
        return departmentid;
    }

    /**
    * <p>This method is used to set the departmentid variable.</p>
    * @author Christopher Adkins
    * @param departmentid
    */
    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }
    
    /**
    * <p>This method is used to get the terminalid variable.</p>
    * @author Christopher Adkins
    * @return terminalid
    */
    public int getTerminalid() {
        return terminalid;
    }
    
    /**
    * <p>This method is used to set the terminalid variable.</p>
    * @author Christopher Adkins
    * @param terminalid
    */
    public void setTerminalid(int terminalid) {
        this.terminalid = terminalid;
    }
    
    /**
    * <p>This method is used to get the description variable.</p>
    * @author Christopher Adkins
    * @return description
    */
    public String getDescription() {
        return description;
    }
    
    /**
    * <p>This method is used to set the description variable.</p>
    * @author Christopher Adkins
    * @param description
    */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
    * <p>This method is used to format the information contained in a Department object as a string.</p>
    * @author Christopher Adkins
    * @return The formatted string
    */
    @Override
    public String toString() {
        return String.format("#%d (%s), Terminal ID: %d", departmentid, description, terminalid);
    }
}
