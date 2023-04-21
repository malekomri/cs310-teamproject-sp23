package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import edu.jsu.mcis.cs310.tas_sp23.Shift;
import java.sql.*;

/**
* <p>DAO class for handling Shift objects.</p>
* @author Malek Omri, Drake Johnson
*/
public class ShiftDAO { 
    private static final String QUERY_FIND = "SELECT * FROM shift WHERE id = ?";
    private final DAOFactory daoFactory;
    
    ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory; }
        
    /**
    * <p>This method is used to create a Shift object using the constructors by searching for an id in the database.</p>
    * @author Oakley Pate, Martell Norman
    * @param id
    * @return Shift object created using given id
    */
        public Shift find(int id) {
            Shift shift = null;
            PreparedStatement ps = null;
            ResultSet rs = null; 
            
            try { Connection conn = daoFactory.getConnection();
                if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_FIND);
                ps.setInt(1, id);
                boolean hasresults = ps.execute();
            if (hasresults) {
                rs = ps.getResultSet();
            while (rs.next()) { 
                String description = rs.getString("description");
                String shiftStart = rs.getString("shiftstart");
                String shiftStop = rs.getString("shiftstop");
                String roundInteral = rs.getString("roundinterval");
                String gracePeriod = rs.getString("graceperiod");
                String dockPenalty = rs.getString("dockpenalty");
                String lunchStart = rs.getString("lunchStart");
                String lunchStop = rs.getString("lunchStop");
                String lunchThreshold = rs.getString("lunchThreshold");

            shift = new Shift(id, description, shiftStart, shiftStop, roundInteral, gracePeriod, dockPenalty, lunchStart, lunchStop, lunchThreshold); } 
            } 
            } 
            } 
            catch (SQLException e) 
            { throw new DAOException(e.getMessage()); } 

            finally { 
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
                } } 
                return shift; 
            }

 /**
    * <p>This method is used to create a Shift object using the constructors by searching for a badge in the database.</p>
    * @author Malek Omri, Drake Johnson
    * @param badge
    * @return Shift object created using given badge
    */
 public Shift find(Badge badge) {
    Shift shift = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    try {
        Connection conn = daoFactory.getConnection();
        if (conn.isValid(0)) {
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
}};