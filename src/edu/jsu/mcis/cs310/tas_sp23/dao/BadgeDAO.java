package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Badge;
import java.sql.*;

public class BadgeDAO {

    private static final String QUERY_FIND = "SELECT * FROM badge WHERE id = ?";

    private final DAOFactory daoFactory;

    BadgeDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    public Badge find(String id) {

        Badge badge = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, id);

                rs = ps.executeQuery();

                if (rs.next()) {

                    String description = rs.getString("description");
                    badge = new Badge(id, description);

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
