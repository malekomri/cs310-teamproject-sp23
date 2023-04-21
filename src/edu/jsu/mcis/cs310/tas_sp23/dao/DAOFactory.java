package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.sql.*;

import edu.jsu.mcis.cs310.tas_sp23.Department;

public final class DAOFactory {

    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_USERNAME = "username";
    private static final String PROPERTY_PASSWORD = "password";

    private final String url, username, password;
    
    private Connection conn = null;

    public DAOFactory(String prefix) {

        DAOProperties properties = new DAOProperties(prefix);

        this.url = properties.getProperty(PROPERTY_URL);
        this.username = properties.getProperty(PROPERTY_USERNAME);
        this.password = properties.getProperty(PROPERTY_PASSWORD);

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }

    }

    Connection getConnection() {
        return conn;
    }
    
    /**
    * <p>This method is used to get the BadgeDAO.</p>
    * @return BadgeDAO
    */
    public BadgeDAO getBadgeDAO() {
        return new BadgeDAO(this);
    }
    
    /**
    * <p>This method is used to get the PunchDAO.</p>
    * @return PunchDAO
    */
    public PunchDAO getPunchDAO() {
        return new PunchDAO(this);
    }
    
    /**
    * <p>This method is used to get the ShiftDAO.</p>
    * @return ShiftDAO
    */
    public ShiftDAO getShiftDAO() {
        return new ShiftDAO(this);
    }
    
    /**
    * <p>This method is used to get the EmployeeDAO.</p>
    * @return EmployeeDAO
    */
    public EmployeeDAO getEmployeeDAO() {
        return new EmployeeDAO(this);
    }
    
    /**
    * <p>This method is used to get the DepartmentDAO.</p>
    * @return DepartmentDAO
    */
    public DepartmentDAO getDepartmentDAO() {
        return new DepartmentDAO(this);
    }
    
    /**
    * <p>This method is used to get the department object.</p>
    * @param departmentID
    * @return Department object
    */
    public Department getDepartment(int departmentID) {
        DepartmentDAO departmentDAO = getDepartmentDAO();
        return departmentDAO.find(departmentID);
    }
    
}
