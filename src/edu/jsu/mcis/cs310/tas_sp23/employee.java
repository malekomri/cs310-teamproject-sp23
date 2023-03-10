package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;

import edu.jsu.mcis.cs310.tas_sp23.dao.DAOFactory;
import edu.jsu.mcis.cs310.tas_sp23.dao.DepartmentDAO;

public class Employee {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDateTime active;
    private Badge badge;
    private Department department;
    private Shift shift;
    private EmployeeType type;

    public Employee(int id, String firstName, String middleName, String lastName, LocalDateTime active, Badge badge, Department department, Shift shift, EmployeeType type) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.active = active;
        this.badge = badge;
        this.department = department;
        this.shift = shift;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getActive() {
        return active;
    }

    public Badge getBadge() {
        return badge;
    }

    public Department getDepartment() {
       
        return department; 
    }
    
    

    public Shift getShift() {
        return shift;
    }

    public EmployeeType getType() {
        return type;
    }

    @Override
    public String toString() {
        String name = String.format("%s%s%s", firstName, middleName != null ? " " + middleName + " " : " ", lastName);
        String badgeDescription = badge.getDescription() != null ? badge.getDescription() : "";
        String badgeId = badge.getId() != null ? badge.getId() : "";
        String departmentDescription = department.getDescription() != null ? department.getDescription() : "";
        return String.format("#%d %s (%s, %s) [%s] (%s) %s", id, name, badgeDescription, badgeId, type.toString(), departmentDescription, active.toString());
    }
}
