package edu.jsu.mcis.cs310.tas_sp23.dao;

import edu.jsu.mcis.cs310.tas_sp23.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
* <p>DAO class for handling Department objects.</p>
* @author Christopher Adkins
*/
public class DepartmentDAO {
    private final DAOFactory daoFactory;

    public DepartmentDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    private static final String QUERY_FIND = "SELECT * FROM department WHERE id = ?";
    
    /**
    * <p>This method is used to create a Department object using the constructors by searching for an id in the database.</p>
    * @author Christopher Adkins
    * @param id
    * @return Department object created using given id
    */
    public Department find(int id) {
        Department department = null;

        PreparedStatement ps = null;
        ResultSet rs = null;

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

        return department;
    }
}
