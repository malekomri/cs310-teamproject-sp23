package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Employee;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Department;
import edu.jsu.mcis.cs310.tas_sp23.Department;
import edu.jsu.mcis.cs310.tas_sp23.EmployeeType;
import edu.jsu.mcis.cs310.tas_sp23.Shift;

import java.sql.*;
import java.time.LocalDateTime;

public class EmployeeDAO {
    
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM employee WHERE id = ?";
    
    private final DAOFactory daoFactory;
    
    public EmployeeDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Employee find(int id) throws SQLException {
        Employee employee = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Connection conn = daoFactory.getConnection();
            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_FIND_BY_ID);
                ps.setInt(1, id);
                boolean hasResults = ps.execute();
                if (hasResults) {
                    rs = ps.getResultSet();
                    if (rs.next()) {
                        String firstName = rs.getString("firstname");
                        String middleName = rs.getString("middlename");
                        String lastName = rs.getString("lastname");
                        String badgeId = rs.getString("badgeid");
                        int typeId = rs.getInt("employeetypeid");
                        int departmentId = rs.getInt("departmentid");
                        LocalDateTime active = rs.getTimestamp("active").toLocalDateTime();
                        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                        Badge badge = badgeDAO.find(badgeId);
                        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
                        Department department = departmentDAO.find(departmentId);
                        EmployeeType type = EmployeeType.values()[typeId - 1];
                        employee = new Employee(id, firstName, middleName, lastName, active, badge, department, null, type);
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Unable to find employee with ID " + id, e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new SQLException(e.getMessage(), e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new SQLException(e.getMessage(), e);
                }
            }
        }
        return employee;
    }
    
    
    public Employee find(Badge badge) {

        Employee employee = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try (Connection conn = daoFactory.getConnection();) {
            
            pstmt = conn.prepareStatement("SELECT * FROM employee WHERE badgeid = ?");
            pstmt.setString(1, badge.getId());
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String middlename = rs.getString("middlename");
                String lastname = rs.getString("lastname");
                LocalDateTime active = rs.getTimestamp("active").toLocalDateTime();
                int employeeTypeInt = rs.getInt("employeetypeid");
                int departmentId = rs.getInt("departmentid");
                
                DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
                Department department = departmentDAO.find(departmentId);
                
                ShiftDAO shiftDAO = daoFactory.getShiftDAO();
                Shift shift = shiftDAO.find(id);
                
                EmployeeType employeeType = EmployeeType.values()[employeeTypeInt];
                
                employee = new Employee(id, firstname, middlename, lastname, active, badge, department, shift, employeeType);
                
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
        
        if (employee == null) {
            throw new DAOException("No employee found with badge ID " + badge.getId());
        }
        
        return employee;
    }
}
    
