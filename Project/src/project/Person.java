package project;
/**
 * This class is used to create the Person object that stores information about the person involved in the project
 *
 * @author Chris Bagalwa
 * @version  8.0.1, 2022-09-10
 */
public class Person {
    // Attributes
    public String name;
    public String userType;
    public String telephoneNumber;
    public String emailAddress;
    public String physicalAddress;

    /** Person constructor with four arguments
     *
     * @param name              String contains the name of the person
     * @param userType          String contains the user type of the person
     * @param telephoneNumber   String contains the telephone number of the person
     * @param emailAddress      String contains the email address of the person
     * @param physicalAddress   String contains the physical address of the person
     */
    public Person(String name, String userType, String telephoneNumber, String emailAddress, String physicalAddress)
    {
        this.name = name;
        this.userType = userType;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
        this.physicalAddress = physicalAddress;
    }

    // Define the method that will return the argument name as a string
    public String getName()
    {
        return name;
    }
    // Define the method that will return the argument userType as a string
    public String getUserType()
    {
        return userType;
    }
    // Define the method that will return the argument telephone number as a string
    public String getTelephone()
    {
        return telephoneNumber;
    }
    // Define the method that will return the argument email address as a string
    public String getEmailAddress()
    {
        return emailAddress;
    }
    // Define the method that will return the argument physical address as a string
    public String getPhysiclAddress()
    {
        return physicalAddress;
    }

    /** @return All the arguments in the other methods
     */
    // Define the toString method that will return all the arguments in other methods defined above
    public String toString()
    {
        String output = "Person Name: " + name + "\n";
        output += "userType: " + userType + "\n";
        output += "Telephone number: " + telephoneNumber + "\n";
        output += "Email address: " + emailAddress + "\n";
        output += "Physical address: " + physicalAddress + "\n";
        return output;
    }
    /**
     *
     * @return  String method Returns formatted person details
     */
    public String personDetails() {
        return userType + "\nName: " + name + "\nTelephone Number: " + telephoneNumber
                + "\nEmail address: " + emailAddress + "\nPhysical Address: " + physicalAddress;
    }
}