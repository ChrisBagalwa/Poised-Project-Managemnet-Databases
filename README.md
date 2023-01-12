# Poised-Project-Managemnet-Databases
This program provides an efficient and user-friendly way to manage construction projects, by allowing users to easily access and update project details, and by providing useful information such as incomplete and overdue projects.
# Description
This program is a Java application that manages construction projects by storing and maintaining details of both pending and completed projects. It allows users to access existing projects, add updates, finalize them, or add new projects at runtime. The program uses a menu-based interface to navigate through the various options.

The Main class is the entry point of the application, it contains the main method that runs the program. It prompts the user to choose from a list of options, such as displaying all the projects, adding new projects, editing existing projects, and displaying incomplete or overdue projects.

The program utilizes several Java packages such as java.io, java.sql, java.time, java.util, and java.util.stream for various functions such as input/output, database access, date manipulation, and stream processing. The java.io package is used to handle IOExceptions that may occur when reading or writing data to the database, and the java.sql package is used to handle SQLExceptions that may occur when interacting with the database. The java.time package is used to handle date and time related operations, such as parsing and formatting date strings, and the java.util and java.util.stream packages are used for various utility functions and stream processing.

The program also includes a Project class and a Personnel class that contain the details of the projects and the personnel assigned to them, such as project number, name, type, ERF number, deadline, total fee, and total paid. The Project class also includes methods to update the project details, such as updating the project status, adding/removing personnel, and calculating the remaining balance. The Personnel class contains details such as name, telephone number, email address, and physical address.

The program also uses a SelectData class to access and retrieve data from the database. This class contains methods to display all the projects, display incomplete projects, display overdue projects, and retrieve details of a specific project by project number.

The program also has some exception handling in place, such as catching IOExceptions and SQLExceptions, and handling invalid input from the user. The program also checks if the date format entered by the user is in the correct format, and prompts the user to enter it again if it is not.
