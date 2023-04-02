package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Shift;
import java.sql.*;

public class ShiftDAO {
    
    //MySQL query statement
    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";

    private final DAOFactory daoFactory;

    ShiftDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
    //Find by id method
    public Shift find(int id) {

        Shift shift = null;

        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);
                
                boolean hasresults = ps.execute();
                
                if (hasresults) {
                    
                    rs = ps.getResultSet();
                    
                    while (rs.next()) {
                        
                        //Initializing shift instances from database keys
                        String description = rs.getString("description");
                        String shiftStart = rs.getString("shiftstart");
                        String shiftStop = rs.getString("shiftstop");
                        String roundInteral = rs.getString("roundinterval");
                        String gracePeriod = rs.getString("graceperiod");
                        String dockPenalty = rs.getString("dockpenalty");
                        String lunchStart = rs.getString("lunchStart");
                        String lunchStop = rs.getString("lunchStop");
                        String lunchThreshold = rs.getString("lunchThreshold");
                        
                        //Building a new shift object
                        shift = new Shift(id, description, shiftStart, shiftStop, roundInteral, gracePeriod, dockPenalty, lunchStart, lunchStop, lunchThreshold);
                        
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
        
        return shift;
        
    }

    //Find by badge method
    public Shift find(Badge badge) {
        
        Shift shift = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                //MySQL query statement
                String query = "SELECT * FROM shift WHERE id = (SELECT shiftid FROM employee WHERE badgeid = ?)";
                
                ps = conn.prepareStatement(query);
                ps.setString(1, badge.getId());
                
                boolean hasResults = ps.execute();
                
                if (hasResults) {
                    
                    rs = ps.getResultSet();
                    
                    while (rs.next()) {
                        
                        int id = rs.getInt("id");
                        String description = rs.getString("description");
                        String shiftStart = rs.getString("shiftstart");
                        String shiftStop = rs.getString("shiftstop");
                        String roundInterval = rs.getString("roundinterval");
                        String gracePeriod = rs.getString("graceperiod");
                        String dockPenalty = rs.getString("dockpenalty");
                        String lunchStart = rs.getString("lunchstart");
                        String lunchStop = rs.getString("lunchstop");
                        String lunchThreshold = rs.getString("lunchthreshold");
                        shift = new Shift(id, description, shiftStart, shiftStop, roundInterval, gracePeriod, dockPenalty, lunchStart, lunchStop, lunchThreshold);
                        
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
        
        return shift;
        
    }
};

/* 

package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Shift;

public class ShiftDAO {
    
    private Connection conn;
    
    public ShiftDAO(DAOFactory daoFactory) {
        this.conn = daoFactory.getConnection();
    }
      
    public Shift find(int shiftId) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Shift shift = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM shift WHERE id = ?");
            stmt.setInt(1, shiftId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                shift = new Shift(rs.getInt("id"), rs.getString("description"),
                    LocalTime.parse(rs.getString("shiftstart"), DateTimeFormatter.ofPattern("HH:mm:ss")),
                    LocalTime.parse(rs.getString("shiftstop"), DateTimeFormatter.ofPattern("HH:mm:ss")),
                    rs.getInt("roundinterval"), rs.getInt("graceperiod"),
                    rs.getInt("dock"), LocalTime.parse(rs.getString("lunchstart"), DateTimeFormatter.ofPattern("HH:mm:ss")),
                    LocalTime.parse(rs.getString("lunchstop"), DateTimeFormatter.ofPattern("HH:mm:ss")),
                    rs.getInt("lunchdeduct"), rs.getInt("shiftduration"), rs.getInt("lunchduration"),
                    rs.getBoolean("earlylunch"));
            }
        }
    } catch (SQLException e) {
        throw new DAOException(e.getMessage());

        }
        finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { }
        }
        return shift;
    }
    
    
    
    public Shift find(Badge badge) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Shift shift = null;
        try {
            stmt = conn.prepareStatement("SELECT shiftid FROM employee WHERE badgeid = ?");
            stmt.setString(1, badge.getId());
            rs = stmt.executeQuery();
            if (rs.next()) {
                int shiftId = rs.getInt("shiftid");
                shift = find(shiftId);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
        return shift;
    }  
}

*/
