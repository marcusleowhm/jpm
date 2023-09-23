### Show Booking Application

#### To run the application
1. Use a build tool like maven and run the `package` command
   - `mvn package -f pom.xml`
2. There will be a jar file created in the `/target` folder with the name of `Case1-1.0-SNAPSHOT.jar`. Use java in a terminal to run the program.
   - `java -jar target/Case1-1.0-SNAPSHOT.jar ` 

#### Getting started
- You can type the `help` command after starting the program to see what actions are available to you.
#### Commands
- User
  - `list`- list out all show numbers in the database
  - `availability <show number>` list out all available seat numbers for a show
  - `book <show number> <phone number> <comma separated list of seats>` - book a ticket based on the desired show number
  - `cancel <ticket number> <phone number>` - cancel an existing ticket
- Admin
  - `list`- list out all show numbers in the database (useful to see what show numbers have already been added)
  - `setup <show number> <number of rows> <seats per row> <cancel window in minutes>` - setup the shows that users can book
  - `view <show number>` - display show number, ticket number, buyer's phone number, seat allocated to buyer

### Program Explanation 
1. The entry point of the program is in `BookingApplication`
2. The console interface is broken up into two parts, depending on whether to assume the User or Admin role.
3. When first starting the program, you will be interacting with the normal User interface
4. **To enter admin mode** - While in User mode, enter the command `enter-godmode` and you will be prompted to enter a password
5. Enter the password `thereisnospoon` and you will be granted access.
6. When using the `exit` command in Admin mode, you will enter the User mode. To enter admin mode again, refer to point 3 above.
7. Depending on your command choice, different `Executable` command objects will be built and executed with a shared `repository` object. You can think of the `Executable` as business logic
8. There is an `InputValidator` class that checks that the inputs from users are valid before the program executes commands

Note:
- The database is entirely in-memory, with a class Repository created in the `dao` package that acts as a layer between interface and the underlying model
- In a bigger application, it is probably better to use a database engine like H2 for in-memory, or MySQL for persistent storage.
- Unit test for the commands covers 96% of lines
