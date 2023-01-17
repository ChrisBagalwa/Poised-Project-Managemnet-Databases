import java.sql.Date;
import java.sql.SQLException;
/**
 *
 * @author Chris Bagalwa
 * @version  8.0.1, 2022-09-10
 */
public class SelectData extends Database {

    /**
     * Constructor
     *
     * @throws SQLException
     */
    SelectData() throws SQLException {
        super();
    }

    /**
     * This method selects all projects from database to display on console
     * @throws SQLException
     */
    void allProjects() throws SQLException {
        results = statement.executeQuery("SELECT * FROM Projects ");
        // Loop over the results, printing them all.
        getResults();
        quit();
    }

    /**
     * This method  selects a specific project from database
     *
     * @param projName String name of specific project
     * @throws SQLException if error occurs
     */
    void project(String projName) throws SQLException {
        results = statement.executeQuery("SELECT * FROM Projects WHERE Proj_Name=" + "\"" + projName + "\"");
        getResults();
        quit();

    }

    /**
     * This method  selects a specific project from database
     *
     * @param projNum Integer number of specific project
     * @throws SQLException ir error occurs
     */
    void project(int projNum) throws SQLException {
        results = statement.executeQuery("SELECT *  FROM Projects WHERE Proj_Num=" + projNum);
        getResults();
        quit();
    }

    /**
     * This method returns contractor info if user changes their details
     *
     * @param projNum Integer number for specific project
     * @throws SQLException
     */
    void contractor(int projNum) throws SQLException {
        results = statement.executeQuery("SELECT *  FROM Contractors WHERE Proj_Num=" + projNum);
        while (results.next()) {
            System.out.println(results.getInt("ID") + ", " + results.getString("Contractor_Name") + ", "
                    + results.getInt("Proj_Num") + ", " + results.getString("Contractor_tel") + ", "
                    + results.getString("Contractor_email") + ", " + results.getString("Contractor_Address") + ", "
            );}
        quit();
    }

    /**
     * This method selects all overdue projects from database (if not finalized)
     *
     * @throws SQLException if error occurs
     */
    void overdueProject() throws SQLException {
        long millis = System.currentTimeMillis();
        Date currentDate = new java.sql.Date(millis);
        results = statement.executeQuery("SELECT *  FROM Projects WHERE Dead_line <= '" + currentDate + "' AND Completion!= 'Finalized'");
        getResults();
        quit();
    }

    /**
     * This method selects all incomplete projects from database
     *
     * @throws SQLException if error occurs
     */
    void incompleteProject() throws SQLException {
        results = statement.executeQuery("SELECT *  FROM Projects WHERE Completion= 'Not Finalized'");
        getResults();
        quit();
    }

    @Override
    void quit() {
    }
}