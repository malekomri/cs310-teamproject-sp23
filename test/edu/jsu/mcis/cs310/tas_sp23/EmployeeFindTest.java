package edu.jsu.mcis.cs310.tas_sp23;

import edu.jsu.mcis.cs310.tas_sp23.dao.BadgeDAO;
import edu.jsu.mcis.cs310.tas_sp23.dao.DAOFactory;
import edu.jsu.mcis.cs310.tas_sp23.dao.EmployeeDAO;
import edu.jsu.mcis.cs310.tas_sp23.Employee;



import org.junit.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

public class EmployeeFindTest {

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");

    }

    @Test
    public void testFindEmployee1() throws SQLException {
        
       
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();

        /* Retrieve Employee from Database (by ID) */

        Employee e1 = employeeDAO.find(14);
        
        /* Compare to Expected Values */
        
        assertEquals("ID #14: Donaldson, Kathleen C (#229324A4), Type: Full-Time, Department: Press, Active: 02/02/2017", e1.toString());

    }
    
    @Test
    public void testFindEmployee2() {
        
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Retrieve Employee from Database (by badge) */

        Badge b = badgeDAO.find("ADD650A8");
        Employee e2 = employeeDAO.find(b);

        /* Compare to Expected Values */
        
        assertEquals("ID #82: Taylor, Jennifer T (#ADD650A8), Type: Full-Time, Department: Office, Active: 02/13/2016", e2.toString());

    }
    
    @Test
    public void testFindEmployee3() throws SQLException {
        
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();

        /* Retrieve Employee from Database (by ID) */

        Employee e3 = employeeDAO.find(127);

        /* Compare to Expected Values */
        
        assertEquals("ID #127: Elliott, Nancy L (#EC531DE6), Type: Temporary / Part-Time, Department: Shipping, Active: 09/22/2015", e3.toString());

    }
    
    @Test
    public void testFindEmployee4() {
        
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Retrieve Employee from Database (by badge) */

        Badge b = badgeDAO.find("C1E4758D");
        Employee e4 = employeeDAO.find(b);

        /* Compare to Expected Values */
        
        assertEquals("ID #93: Leist, Rodney J (#C1E4758D), Type: Temporary / Part-Time, Department: Warehouse, Active: 10/09/2015", e4.toString());

    }

    @Test
    public void testFindEmployee5() throws SQLException {


        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();

        /* Retrieve Employee from Database (by ID) */

        Employee e5 = employeeDAO.find(93);

        /* Compare to Expected Values */

        assertEquals("ID #93: Leist, Rodney J (#C1E4758D), Type: Temporary / Part-Time, Department: Warehouse, Active: 10/09/2015", e5.toString());

    }

    @Test
    public void testFindEmployee6() {

        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Retrieve Employee from Database (by badge) */

        Badge b = badgeDAO.find("021890C0");
        Employee e6 = employeeDAO.find(b);

        /* Compare to Expected Values */

        assertEquals("ID #1: Chapell, George R (#021890C0), Type: Temporary / Part-Time, Department: Assembly, Active: 04/02/2016", e6.toString());

    }

    @Test
    public void testFindEmployee7() {

        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Retrieve Employee from Database (by badge) */

        Badge b = badgeDAO.find("0FFA272B");
        Employee e7 = employeeDAO.find(b);

        /* Compare to Expected Values */

        assertEquals("ID #8: Corwin, John L (#0FFA272B), Type: Full-Time, Department: Press, Active: 10/11/2015", e7.toString());

    }

    @Test
    public void testFindEmployee8() throws SQLException {


        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();

        /* Retrieve Employee from Database (by ID) */

        Employee e8 = employeeDAO.find(8);

        /* Compare to Expected Values */

        assertEquals("ID #8: Corwin, John L (#0FFA272B), Type: Full-Time, Department: Press, Active: 10/11/2015", e8.toString());

    }

    @Test
    public void testFindEmployee9() {

        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

        /* Retrieve Employee from Database (by badge) */

        Badge b = badgeDAO.find("08D01475");
        Employee e9 = employeeDAO.find(b);

        /* Compare to Expected Values */

        assertEquals("ID #4: Littell, Amie D (#08D01475), Type: Full-Time, Department: Grinding, Active: 01/22/2017", e9.toString());

    }
}