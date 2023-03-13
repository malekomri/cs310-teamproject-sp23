package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO {
    private final DAOFactory daoFactory;

    public DepartmentDAO(DAOFactory daoFactory) {
<<<<<<< HEAD
        this.daoFactory = daoFactory;
=======

        this.daoFactory = daoFactory;

>>>>>>> department
    }

    private static final String QUERY_FIND = "SELECT * FROM department WHERE id = ?";

    public Department find(int id) {
<<<<<<< HEAD
        Department department = null;
=======
        Department deparment = null;
>>>>>>> department

        PreparedStatement ps = null;
        ResultSet rs = null;

<<<<<<< HEAD
        try (Connection conn = daoFactory.getConnection()) {

            ps = conn.prepareStatement(QUERY_FIND);
            ps.setInt(1, id);

            boolean hasresult = ps.execute();

            if (hasresult) {

                rs = ps.getResultSet();

                while (rs.next()) {

                    int departmentid = rs.getInt("id");
                    int terminalid = rs.getInt("terminalid");
                    String description = rs.getString("description");

                    department = new Department(departmentid, terminalid, description);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
=======
        try {

            Connection conn = daoFactory.getConnection();

            if (conn.isValid(0)) {

                ps = conn.prepareStatement(QUERY_FIND);
                ps.setString(1, String.valueOf(id));

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

>>>>>>> department
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
<<<<<<< HEAD
                    throw new DAOException(e.getMessage());
=======
                    throw  new DAOException(e.getMessage());
>>>>>>> department
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

<<<<<<< HEAD
        return department;
    }
}
=======
        return deparment;

    }
}
>>>>>>> department
