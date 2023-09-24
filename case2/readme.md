### Mars Rover Launcher

#### To run the application
1. Use a build tool like maven and run the `package` command
   - `mvn clean package -Dmaven.test.skip=true`
2. There will be a jar file created in the `/target` folder with the name of `case2-0.0.1-SNAPSHOT.jar`. Use java in a terminal to run the program.
    - `java -jar target/case2-0.0.1-SNAPSHOT.jar` 

#### Getting started
- You can type `help` after starting the program to see a list of actions available to you.

#### Commands
- `list` - shows a list of rovers that are already launched on the planet
- `launch <x,y,d> <f,b,l,r>` launch a rover at coordinate (x, y), facing direction `d` (N, S, E, W) and commanding it to move according to the directions `(f, b, l, or r)`
- `launch --multi` Shifts the context into a multi launch interface
  - The program will prompt you to enter starting coordinates and string of commands to issue the rover line by line, until the `commit` command is entered
  - `cancel` - Cancels the launch and reinstate user interface to the original 
- `issue <rover id> <f, b, l, r>` issue a string of command to a rover that is already launched to carry out movement commands. Use the `list` command to list out the rovers and see their ID.

### Program Explanation
1. The entry point of the program is RoverApplication. It implements CommandLineRunner as a SpringApplication, allowing us to inject a console based user interface using the Scanner class
2. When launching multiple rovers using `launch --multi`, **the rovers are assumed to be launched, commanded to move, before the next rover is launched.**
3. While moving the rover after launching, the program ignores the command when there is a rover in the coordinate it is supposed to enter next
4. There is an `InputValidator` class that checks that the inputs from users are valid before the program executes commands
