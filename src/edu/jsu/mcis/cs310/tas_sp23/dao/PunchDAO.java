package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Punch;
import edu.jsu.mcis.cs310.tas_sp23.Badge;
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
    
    /*
        The find method takes an id and creates a punch object using the information from the database. 
        Right now, my plan is to use the badgeid to search for the corresponding ids in the database, 
        test if the id matches with the given timestamp, and then feed that into the find method and the add it to the list.
    */
    
    public ArrayList<Punch> list(Badge badge, LocalDate timestamp) {

        ArrayList<Punch> list = new ArrayList<>();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND_BADGEID);
                ps.setString(1, badge.getId());
                ps.setDate(2, java.sql.Date.valueOf(timestamp));

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();
                    PunchDAO punchDAO = new PunchDAO(daoFactory);

                    while (rs.next()) {
                        
                        Integer id = rs.getInt("id");
                        
                        Punch punch = punchDAO.find(id);
                        list.add(punch);
                        String timeString = rs.getString("timestamp");
                        LocalDate date = LocalDate.parse(timeString, formatter);
                        
                        if(timestamp.equals(date)) {
                            
                            list.add(find(id));
                            
                        }
                        
                    }
                    
                    //If last pucnh is CLOCK_IN, next day must be checked for closing pair
                    int lastIndex = list.size();
                    Punch lastPunchIndex = list.get(lastIndex - 1);
                    
                    EventType lastPunch = lastPunchIndex.getPunchtype();
                    
                    if(lastPunch == EventType.CLOCK_IN){
                        //ps = conn.prepareStatement(QUERY_LIST_NEXT_DAY);
                        
                        ps.setString(1,badge.getId());
                        ps.setDate(2, java.sql.Date.valueOf(timestamp));
                        
                        boolean hasresults2 = ps.execute();
                        
                        if(hasresults2){
                            
                            rs = ps.getResultSet();
                            
                            while(rs.next()){
                                
                                //Find Punch type for next day
                                
                                Integer id = rs.getInt("id");
                                Punch firstPunchDay3 = punchDAO.find(id);
                                EventType firstPunchOfDay = firstPunchDay3.getPunchtype();
                                
                                if ((firstPunchOfDay == EventType.CLOCK_OUT) || (firstPunchOfDay == EventType.TIME_OUT)){
                                    
                                    list.add(firstPunchDay3);
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
    
    public Punch create() {
        return null;
    }
}