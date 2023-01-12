/** This program manages construction projects by storing pending and
 * complete project details, as well as maintaining updates about the project.
 * @author Chris Bagalwa
 * @version  8.0.1, 2022-09-10
 */

// Import the packages needed to display project overview
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Collectors;

import project.*;

// ProjectOverview class declaration
public class Main {
    // Define variables used for project and project personnel details.
    static String name;
    static String userType;
    static String telephoneNumber, emailAddress , physicalAddress;
    static String projectNumber, projectName, projectType, ERFNumber, deadLine;
    static double totalFee, totalPaid;


    /** Use the main method to access the existing projects, add updates,
     * finalize them, or add new projects at runtime. <br>
     *
     * @param args The command line arguments
     * @throws IOException if error occurs
     */
    public static void main(String[] args) throws SQLException {
        // Prompt the user to choose from the menu list above
        while (true) {
            try {
                System.out.println("""
						\nProgram menu:
						1 - Display all the projects
						2 - Add new a project
						3 - Edit existing project
						4 - Display incomplete projects
						5 - Display overdue projects
						6 - Select a project
						0 - exit
						Enter option:""");

                @SuppressWarnings("resource")
                Scanner input = new Scanner(System.in);
                int option = input.nextInt();
                // If user chooses option 1, then display the list of all the projects
                if (option == 1) {
                    new SelectData().allProjects();
                // Else if the user chooses option 2, then prompt the user to input project details
                } else if (option == 2) {
                    Project newProject;
                    input.nextLine();
                    System.out.println("\nEnter Project number:");
                    projectNumber = input.nextLine();
                    System.out.println("Enter Project name:");
                    projectName = input.nextLine();
                    System.out.println("Enter Project type:");
                    projectType = input.nextLine();
                    System.out.println("Enter building ERF number:");
                    ERFNumber = input.nextLine();
                    System.out.println("Enter project deadline(yyyy-MM-dd):");
                    deadLine = input.nextLine();
                    while (validDateFormat(deadLine) == false) {
                        System.out.println("\nInvalid date format. Try again.");
                        System.out.println("Enter project deadline(yy-MM-dd):");
                        deadLine = input.nextLine();
                    }
                    while (true) {
                        try {
                            System.out.println("Enter overall Project Fee:");
                            totalFee = input.nextDouble();
                            System.out.println("Enter total paid to date:");
                            totalPaid = input.nextDouble();
                            // New project Object
                            newProject = new Project(projectNumber,projectName,projectType,ERFNumber,deadLine,totalFee,totalPaid);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid Input!\nPlease enter valid amount.");
                            input.nextLine();
                        }
                    }
                    // created person objects, they're accessible out of the loops
                    Person customer = null;
                    Person architect = null;
                    Person engineer;
                    Person manager = null;
                    Person contractor = null;

                    // use loop to add details of project customer, architect and contractor, manager and engineer
                    System.out.println("\nCustomer Details".toUpperCase());
                    input.nextLine();
                    int loop = 0;

                    while (true) {
                        loop++;
                        createPersons();
                        if (loop == 1) {
                            userType = "Customer";
                            customer = new Person(toTitleCase(name.strip()),toTitleCase(userType.strip()),
                                    telephoneNumber.strip(), emailAddress.strip(), toTitleCase(physicalAddress.strip()));
                            if (projectName.isBlank()) {
                                // if project name not provided Building type & client surname used in place of.
                                newProject.projectName = newProject.getProjectType() + " " + name;
                            }
                            System.out.println("\nProject Manager Details".toUpperCase());
                        } else if (loop == 2) {
                            userType = "Manager";
                            manager = new Person(toTitleCase(name.strip()),toTitleCase(userType),
                                    telephoneNumber.strip(), emailAddress.strip(),
                                    toTitleCase(physicalAddress.strip()));
                            System.out.println("\nProject Architect Details".toUpperCase());
                        } else if (loop == 3) {
                            userType = "Architect";
                            architect = new Person(toTitleCase(name.strip()), toTitleCase(userType.strip()),
                                    telephoneNumber.strip(), emailAddress.strip(),
                                    toTitleCase(physicalAddress.strip()));
                            System.out.println("\nProject Contractor Details".toUpperCase());
                        } else if (loop == 4) {
                            userType = "Contractor";
                            contractor = new Person(toTitleCase(name.strip()), toTitleCase(userType.strip()),
                                    telephoneNumber.strip(), emailAddress.strip(),
                                    toTitleCase(physicalAddress.strip()));
                            System.out.println("\nProject Engineer Details".toUpperCase());
                        }
                        else {
                            userType = "Engineer";
                            engineer = new Person(toTitleCase(name.strip()), toTitleCase(userType.strip()),
                                    telephoneNumber.strip(), emailAddress.strip(),
                                    toTitleCase(physicalAddress.strip()));
                            break;

                        }
                    }
                    // Assert used to declare null values as false.
                    System.out.println("\n\nInformation Successfully Saved!\n\n");
                    System.out.println(newProject.projectDetails() + "\n\n");
                    System.out.println(customer.personDetails() + "\n\n");
                    System.out.println(manager.personDetails() + "\n\n");
                    System.out.println(architect.personDetails() + "\n\n");
                    System.out.println(contractor.personDetails() + "\n\n");
                    System.out.println(engineer.personDetails());

                    new InsertData().project(newProject, customer, manager, architect, contractor, engineer);

                // Else if the user chooses option 3, then prompt the user to input specifies project to edit
                } else if (option == 3) {
                    input.nextLine();
                    System.out.println("\nEnter Project number:");
                    int projectToEdit = input.nextInt();
                    new SelectData().project(projectToEdit);
                    while (true) {
                        try {
                            System.out.println("""
									\n
									1 - Edit contractor's details
									2 - Edit project deadline
									3 - Edit Amount paid to date
									4 - Finalize project
									0 - Return to main menu
									""");

                            int choice = input.nextInt();
                            if (choice == 1) {
                                input.nextLine();
                                System.out.println("\nEnter contractor's new telephone number:");
                                telephoneNumber = input.nextLine();
                                System.out.println("Enter contractor's new email Address: ");
                                emailAddress = input.nextLine();
                                new UpdateData().setContractorDetails(projectToEdit, telephoneNumber.strip(), emailAddress.strip());
                                new SelectData().contractor(projectToEdit);
                            } else if (choice == 2) {
                                input.nextLine();
                                System.out.println("\nEnter New deadline(YYYY-MM-DD):");
                                deadLine = input.nextLine();
                                while ((validDateFormat(deadLine)) == false) {
                                    System.out.println("\nInvalid date format. Try again." + deadLine);
                                    System.out.println("\nEnter New deadline(YYYY-MM-DD):");
                                    deadLine = input.nextLine();
                                }
                                new UpdateData().setDeadLine(projectToEdit, deadLine);

                            } else if (choice == 3) {
                                while (true) {
                                    try {
                                        System.out.println("\nEnter amount paid:");
                                        Double paidAmount = input.nextDouble();
                                        new UpdateData().setTotalPaid(projectToEdit, paidAmount);
                                        break;
                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid Input!\nPlease enter valid amount.");
                                        input.nextLine();
                                    }
                                }

                            } else if (choice == 4) {
                                new UpdateData().setAsFinalized(projectToEdit);
                            } else if (choice == 0) {
                                break;
                            } else {
                                System.out.println("Invalid option!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input, try again!");
                            input.nextLine();
                        }
                    }
                // Else if the user chooses option 4, then Display incomplete projects
                } else if (option == 4) {
                    new SelectData().incompleteProject();
                // Else if the user chooses option 5, then Display overdue projects
                } else if (option == 5) {
                    new SelectData().overdueProject();

                } else if (option == 6) {

                    input.nextLine();
                    String projectToEdit;
                    System.out.println("\nEnter project name or number:");
                    projectToEdit = input.nextLine();

                    try {
                        new SelectData().project(Integer.parseInt(projectToEdit));
                    } catch (NumberFormatException e) {
                        new SelectData().project(toTitleCase(projectToEdit.strip()));
                    }
                // Else if the user chooses option 0, Logout
                } else if (option == 0) {
                    System.out.println("\nLogout Successful!");
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again!");
            }
        }

    }

    /**
     * Method for entering person object details
     */
    public static void createPersons() {
        try {
            @SuppressWarnings("resource")
            Scanner input = new Scanner(System.in);
            System.out.println("\nEnter name:");
            name = input.nextLine();
            System.out.println("Enter telephone number:");
            telephoneNumber = input.nextLine();
            System.out.println("Enter email address:");
            emailAddress = input.nextLine();
            System.out.println("Enter physical address:");
            physicalAddress = input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!\nPlease enter valid amount.");
        }
    }

    /** Define the validDateFormat method that validates if user inputs
     * the correct date format for the project deadlines
     *
     * @param deadLine formats the string to date
     * @return boolean value if date is valid
     */

    public static boolean validDateFormat(String deadLine) {
        boolean valid;
        try {
            String dateFormat = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
                    .withResolverStyle(ResolverStyle.LENIENT);
            LocalDate date = LocalDate.parse(deadLine, dateFormatter);
            valid = true;
        } catch (DateTimeParseException e) {
            valid = false;
        }
        return valid;
    }

    /**
     * Define the recursive method that convert strings to title case. <br>
     *
     * @param string is the string to be converted to title case.
     * @return returns the formatted string.
     */

    public static String toTitleCase(String string) {
        String word_separator = " ";
        if (string == null || string.isEmpty()) {
            return string;
        }
        // Splits string at space and converts each 1st character of substring to
        // title case and following characters to lower case
        return Arrays.stream(string.split(word_separator)).map(
                        word -> word.isEmpty() ? word : Character.toTitleCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(word_separator));
    }

}