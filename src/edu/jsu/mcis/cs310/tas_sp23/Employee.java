package edu.jsu.mcis.cs310.tas_sp23;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import edu.jsu.mcis.cs310.tas_sp23.dao.DAOFactory;
import edu.jsu.mcis.cs310.tas_sp23.dao.DepartmentDAO;

/**
* <p>Model class for creating and handling Employee objects.</p>
* @author Malek Omri
*/
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
    
    /**
    * <p>Constructor for creating an Employee object.</p>
    * @author Malek Omri
    * @param id
    * @param firstName
    * @param middleName
    * @param lastName
    * @param active
    * @param badge
    * @param department
    * @param shift
    * @param type
    */
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
    
    /**
    * <p>This method is used to get the id variable.</p>
    * @author Malek Omri
    * @return id
    */
    public int getId() {
        return id;
    }
    
    /**
    * <p>This method is used to get the firstName variable.</p>
    * @author Malek Omri
    * @return firstName
    */
    public String getFirstName() {
        return firstName;
    }
    
    /**
    * <p>This method is used to get the middleName variable.</p>
    * @author Malek Omri
    * @return middleName
    */
    public String getMiddleName() {
        return middleName;
    }
    
    /**
    * <p>This method is used to get the lastName variable.</p>
    * @author Malek Omri
    * @return lastName
    */
    public String getLastName() {
        return lastName;
    }
    
    /**
    * <p>This method is used to get the active variable.</p>
    * @author Malek Omri
    * @return active
    */
    public LocalDateTime getActive() {
        return active;
    }
    
    /**
    * <p>This method is used to get the badge variable.</p>
    * @author Malek Omri
    * @return badge
    */
    public Badge getBadge() {
        return badge;
    }
    
    /**
    * <p>This method is used to get a Department object.</p>
    * @author Malek Omri
    * @return Department object
    */
    public Department getDepartment() {
        DAOFactory daoFactory = new DAOFactory("tas");
        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
        return departmentDAO.find(this.department.getDepartmentid());
    }
    
    /**
    * <p>This method is used to get the shift variable.</p>
    * @author Malek Omri
    * @return shift
    */
    public Shift getShift() {
        return shift;
    }
    
    /**
    * <p>This method is used to get the type variable.</p>
    * @author Malek Omri
    * @return type
    */
    public EmployeeType getType() {
        return type;
    }
    
   /**
    * <p>This method is used to get the Employee type (part-time or full-time) from the id.</p>
    * @author Malek Omri
    * @param id
    * @return EmployeeType
    */
    public static EmployeeType getTypeById(int id) {
        switch (id) {
            case 1:
                return EmployeeType.FULL_TIME;
            case 0:
                return EmployeeType.PART_TIME;
            default:
                throw new IllegalArgumentException("Invalid EmployeeType id: " + id);
        }
    }
    
    
    /**
    * <p>This method is used to format the information contained in an Employee object as a string.</p>
    * @author Malek Omri
    * @return The formatted string
    */
    @Override
    public String toString() {
        String activeDate = active.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String middle = (middleName != null && !middleName.isEmpty()) ? " " + middleName + " " : " ";
        return "ID #" + id + ": " + lastName + ", " + firstName + middle + "(#" + badge.getId() + "), Type: "+ getType()+", Department: " +department.getDescription() + ", Active: " + activeDate;
    }
}
