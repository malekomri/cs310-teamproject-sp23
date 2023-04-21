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

/**
* <p>DAO class for handling Punch objects.</p>
* @author Oakley Pate, Martell Norman
*/
public class PunchDAO {
    
    //SQL Queries
    private static final String QUERY_FIND_ID = "SELECT * FROM event WHERE id = ?";
    private static final String QUERY_LIST_BADGEID = "SELECT *, DATE(timestamp) AS tsdate FROM event WHERE badgeid = ? HAVING tsdate = ? ORDER BY timestamp";
    private static final String QUERY_LIST_BADGEID_NEXTDAY = "SELECT *, DATE(timestamp) AS tsdate FROM event WHERE badgeid = ? HAVING tsdate > ? ORDER BY timestamp LIMIT 1";

    private final DAOFactory daoFactory;

    PunchDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
    /**
    * <p>This method is used to create a Punch object using the constructors by searching for an id in the database.</p>
    * @author Oakley Pate, Martell Norman
    * @param id
    * @return Punch object created using given id
    */
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
    
    /**
    * <p>This method is used to create an ArrayList that contains all of one person's punches on a given day.</p>
    * @author Oakley Pate, Martell Norman
    * @param badge
    * @param timestamp
    * @return the ArrayList that contains the Punch objects
    */
    public ArrayList<Punch> list(Badge badge, LocalDate timestamp) {

        ArrayList<Punch> list = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_LIST_BADGEID);
                ps.setString(1, badge.getId());
                ps.setDate(2, java.sql.Date.valueOf(timestamp));

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {
                        
                        Integer id = rs.getInt("id");
                        
                        list.add(find(id));
                        
                    }
                    
                    //If last punch is CLOCK_IN, next day must be checked for closing pair
                    if (!list.isEmpty()) {
                        
                        int lastIndex = list.size();
                        
                        Punch lastPunch = list.get(lastIndex - 1);
                        
                        EventType lastPunchType = lastPunch.getPunchtype();
                    
                        if (lastPunchType == EventType.CLOCK_IN){
                        
                            ps = conn.prepareStatement(QUERY_LIST_BADGEID_NEXTDAY);
                            ps.setString(1, badge.getId());
                            ps.setDate(2, java.sql.Date.valueOf(timestamp));
                        
                            hasresults = ps.execute();
                        
                            if(hasresults){
                            
                                rs = ps.getResultSet();
                            
                                while(rs.next()){
                                
                                    Integer id = rs.getInt("id");
                                
                                    Punch nextDayPunch = find(id);
                                    EventType nextDayPunchType = nextDayPunch.getPunchtype();
                                        
                                    if ((nextDayPunchType == EventType.CLOCK_OUT) || (nextDayPunchType == EventType.TIME_OUT)){
                                    
                                        list.add(nextDayPunch);
                                    
                                    }
                                }
                            }
                        }
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

        return list;

    }
    
    /**
    * <p>This method is used to aggregate the Punch objects from one person over a given range of dates.</p>
    * @author Oakley Pate
    * @param badge
    * @param begin
    * @param end
    * @return the ArrayList that contains the Punch objects
    */
    public ArrayList<Punch> list(Badge badge, LocalDate begin, LocalDate end) {
            
        ArrayList<Punch> rangeList = new ArrayList<>();
        
        LocalDate currentDay = begin;

        try {
            
            while (currentDay.isBefore(end.plusDays(1))) {
                    
                    rangeList.addAll(list(badge, currentDay));
                    currentDay = currentDay.plusDays(1);
                    
            }
            
        } catch (Exception e) {
            
            throw new DAOException(e.getMessage());
            
        } 
        
        return rangeList;
        
    }
    
    /**
    * <p>This method is used to add new Punches to the database.</p>
    * @author Malek Omri
    * @param punch
    * @return id of the new Punch
    */
    public int create(Punch punch) {
        String badgeId = punch.getBadge().getId();
        LocalDateTime timestamp = punch.getOriginaltimestamp();
    
        try {
            Connection conn = daoFactory.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO event (badgeid, terminalid, eventtypeid, timestamp) " +
                "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, badgeId);
            statement.setInt(2, punch.getTerminalid());
            statement.setInt(3, punch.getPunchtype().ordinal());
            statement.setObject(4, timestamp);
    
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                return 0; // insertion failed, return default value
            }
    
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                punch.setId(id);
                return id;
            } else {
                return 0; // insertion failed, return default value
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
 }

