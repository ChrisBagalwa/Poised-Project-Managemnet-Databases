import java.sql.SQLException;

import project.*;

/**
 *
 * @author Chris Bagalwa
 * @version  8.0.1, 2022-09-10
 */
public class InsertData extends Database {

    /**
     * Constructor
     * @throws SQLException
     */
    InsertData() throws SQLException {
        super();
    }

    /**
     * This method is used to insert projects/ project details into the database.
     *
     * @param newProject Project being added to the database
     * @param customer Person represents project's customer
     * @param architect Person represents project's architect
     * @param contractor Person represents project's contractor
     * @param engineer Person represents project's engineer
     * @param manager Person represents project's customer manager
     * @throws SQLException if error occurs
     */
    public void project(Project newProject, Person customer, Person architect, Person contractor, Person engineer,
                 Person manager) throws SQLException {
        int projectNumber = 0;
        rowsChanged = statement.executeUpdate("INSERT INTO Projects VALUES(default, \"" + newProject.projectNumber
                + "\",\"" + newProject.projectName + "\", \"" + manager.name + "\" \"" + "\"" + ", '" + newProject.projectType + "', '"
                + newProject.physicalAddress + "', '" + newProject.ERFNumber + "', '" + newProject.deadLine + "', "
                + newProject.totalFee + ", " + newProject.totalPaid + ", 'Not Finalized'," + " null, \"" + customer.name
                + "\" \"" + "\", \"" + architect.name + "\" \"" + "\", \"" + contractor.name + "\" \"" + "\", \"" + engineer.name
                + "\");");

        results = statement.executeQuery("SELECT proj_num from projects where proj_name=\"" + newProject.projectName
                + "\" and customer= \"" + customer.name + "\"");
        // getting project Number for child tables
        while (results.next()) {
            projectNumber = results.getInt("proj_Num");
            System.out.println(projectNumber);
        }
        // temporarily disabling foreign key to be able to insert values into child tables
        statement.executeUpdate("Set foreign_key_checks = 0");

        changeTable(customer, projectNumber, "Customers");
        changeTable(architect, projectNumber, "Architects");
        changeTable(contractor, projectNumber, "Contractors");
        changeTable(engineer, projectNumber, "Engineers");
        changeTable(manager, projectNumber, "Managers");

        statement.executeUpdate("Set foreign_key_checks = 1");
        System.out.println("Query Complete " + rowsChanged + " rows changed.");
        quit();
    }

    /**
     * This method adds values into the database person's tables <br>
     *
     * @param person Person Object who's values will be inserted into an appropriate table.
     * @param projectNum Integer for the number of the project person is linked to.
     * @throws SQLException if error occurs.
     */
    private void changeTable(Person person, int projectNum, String tableName) throws SQLException {
        rowsChanged += statement.executeUpdate("INSERT INTO " + tableName + " VALUES(default, \"" + person.name + " "
               + "\", " + projectNum + ", '" + person.telephoneNumber + "', '" + person.emailAddress + "', \""
                + person.physicalAddress + "\");");
    }

    @Override
    void quit() {
    }
}