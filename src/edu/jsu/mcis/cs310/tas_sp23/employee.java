package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DAOFactory daoFactory = new DAOFactory("tas");
        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
        return departmentDAO.find(this.department.getDepartmentid());
    }
    
    
    
    

    public Shift getShift() {
        return shift;
    }

    public EmployeeType getType() {
        return type;
    }

   
   
    
   
    @Override
    public String toString() {
        String activeDate = active.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String middle = (middleName != null && !middleName.isEmpty()) ? " " + middleName + " " : " ";
        return "ID #" + id + ": " + lastName + ", " + firstName + middle + "(#" + badge.getId() + "), Type: " + getType() + ", Department: " + department.getDescription() + ", Active: " + activeDate;
    }}
    
