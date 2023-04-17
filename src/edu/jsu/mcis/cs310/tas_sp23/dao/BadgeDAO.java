package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import java.sql.*;

/**
* <p>DAO class for handling Badge objects.</p>
*/
public class BadgeDAO {

    private static final String QUERY_FIND = "SELECT * FROM badge WHERE id = ?";

    private final DAOFactory daoFactory;

    BadgeDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }
    
    /**
    * <p>This method is used to create a Badge object using the constructors by searching for a given id in the database.</p>
    * @param id
    * @return Badge object created using given id
    */
    public Badge find(String id) {

        Badge badge = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, id);

                boolean hasresults = ps.execute();

                if (hasresults) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        String description = rs.getString("description");
                        badge = new Badge(id, description);

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

        return badge;

    }

}
