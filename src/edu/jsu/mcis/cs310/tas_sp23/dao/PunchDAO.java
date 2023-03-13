package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Department;
import edu.jsu.mcis.cs310.tas_sp23.Employee;
import edu.jsu.mcis.cs310.tas_sp23.EventType;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PunchDAO {

    private static final String QUERY_FIND_ID = "SELECT * FROM event WHERE id = ?";
    private static final String QUERY_FIND_BADGEID = "SELECT * FROM event WHERE badgeid = ?";
    //private static final String QUERY_LIST_NEXT_DAY = "SELECT *, DATE('timestamp') AS tsdate FROM event WHERE badgeid = ? ORDER BY 'timestamp' LIMIT 1;";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    public Punch find(Integer id) {

        Punch punch = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_ID);
                ps.setInt(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        //Getting info from database
                        Integer terminalid = rs.getInt("terminalid");
                        String badgeid = rs.getString("badgeid");
                        Integer punchtypeid = rs.getInt("eventtypeid");
                        String timeString = rs.getString("timestamp");
                        
                        //badgeid to badge object
                        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
                        Badge badge = badgeDAO.find(badgeid);
                        
                        //eventtypeid to punchtype
                        EventType punchtype = EventType.values()[punchtypeid];
                        
                        //DateTime formatting
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime originaltimestamp = LocalDateTime.parse(timeString, formatter);
                        
                        punch = new Punch(id, terminalid, badge, originaltimestamp, punchtype);

                    }

                }

            }

        } catch (SQLException e) {

            throw new DAOException(e.getMessage());

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }

        }

        return punch;
    }



    


    public int create(Punch punch) {

        int punchID = 0;
    
        PreparedStatement psInsert = null;
        PreparedStatement psCheck = null;
        ResultSet rs = null;
    
        try {
    
            Connection conn = daoFactory.getConnection();
            final String QUERY_INSERT = "INSERT INTO event (terminalid, badgeid, eventtypeid, originaltimestamp) VALUES (?, ?, ?, ?)";

    
            // Check if the punch is authorized
            int terminalID = punch.getTerminalid();
            Badge badgeID = punch.getBadge();
            EventType eventType = punch.getPunchtype();
            LocalDateTime originalTimeStamp = punch.getOriginaltimestamp();
            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            Badge badge = badgeDAO.find(badgeID.getId());
            EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
            Employee employee = employeeDAO.find(badgeID);
            DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
            Department department = departmentDAO.find(employee.getId());
            int clockTerminalID = department.getTerminalid();
    
            // Manually added punches are automatically authorized
            if (terminalID == 0) {
                clockTerminalID = 0;
            }
    
            // Check if the punch is authorized
            if (terminalID == clockTerminalID) {
    
                // Insert new punch into the database
                psInsert = conn.prepareStatement(QUERY_INSERT, Statement.RETURN_GENERATED_KEYS);
                psInsert.setInt(1, terminalID);
                psInsert.setString(2, badgeID.getId());
                psInsert.setInt(3, eventType.ordinal());
                psInsert.setString(4, originalTimeStamp.toString());
                psInsert.executeUpdate();
    
                // Get the generated ID of the new punch
                rs = psInsert.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    punchID = rs.getInt(1);
                }
    
            }
    
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (psCheck != null) {
                try {
                    psCheck.close();
                } catch (SQLException e) {
                    throw new DAOException(e.getMessage());
                }
            }
            if (psInsert != null) {
                try {
                    psInsert.close();}
}}}