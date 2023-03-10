package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {
    private final DAOFactory daoFactory;

    public DepartmentDAO(DAOFactory daoFactory) {

        this.daoFactory = daoFactory;

    }

    private static final String QUERY_FIND = "SELECT * FROM department WHERE id = ?";

    public Department find(int id) {
        Department deparment = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, String.valueOf(1));

                boolean hasresult = ps.execute();

                if (hasresult) {

                    rs = ps.getResultSet();

                    while (rs.next()) {

                        int terminalid = rs.getInt("terminalid");
                        int departmentind = rs.getInt("id");
                        String description = rs.getString("description");

                        deparment = new Department(departmentind, terminalid, description);
                    }
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw  new DAOException(e.getMessage());
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

        return deparment;

    }
}