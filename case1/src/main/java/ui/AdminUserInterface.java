package ui;

import command.ListCommand;
import command.SetupCommand;
import command.ViewCommand;
import dao.Repository;
import message.Messages;
import utility.InputValidator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * @author Marcus LEOW
 */
public class AdminUserInterface {

    private static final String encryptedPassword = "f875eba085941cc78509bd3482dc0294";

    public static void runMainLoop(Repository repo) {

        try {
            if (!isAuthenticated()) {
                System.out.println("Incorrect password");
                return;
            }
        } catch (NoSuchAlgorithmException ex) {
            return;
        }

        System.out.println("Entering admin mode.....");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("──────────────────────────────────────────────────────────────────────────");
            System.out.println("Type 'help' to list the commands. Type 'exit' to exit admin mode.");
            System.out.print("> ");

            String line = sc.nextLine();

            //Trim extra white space between params
            //Split line into respective params
            String[] inputs = line.replaceAll("\\s{2,}"," ").split(" ");

            String command = inputs[0].toLowerCase();
            if (command.equals("exit")) {
                break;
            }

            if (command.equals("list")) {
                new ListCommand().run(repo);
                continue;
            }

            if (command.equals("setup")) {
                if (InputValidator.isValidSetupCommand(inputs)) {
                    SetupCommand executor = SetupCommand.builder()
                            .userInputShowNumber(inputs[1])
                            .userInputNumOfRows(Integer.parseInt(inputs[2]))
                            .userInputNumOfSeats(Integer.parseInt(inputs[3]))
                            .userInputCancellationWindow(Integer.parseInt(inputs[4]))
                            .build();
                    executor.run(repo);
                }
                continue;
            }

            if (command.equals("view")) {
                if (InputValidator.isValidViewCommand(inputs)) {
                    ViewCommand executor = ViewCommand.builder()
                            .userInputShowNumber(inputs[1])
                            .build();
                    executor.run(repo);
                }
                continue;
            }

            if (command.equals("help")) {
                Messages.printAdminHelp();
                continue;
            }

            Messages.printInvalidInputs();
        }
    }

    public static boolean isAuthenticated() throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        StringBuilder s = new StringBuilder();

        String password;
        if (System.console() == null) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter password: ");
            password = scanner.nextLine();
        } else {
            password = new String(System.console().readPassword("Enter password: "));
        }
        m.update(password.getBytes());
        byte[] bytes = m.digest();
        for (byte aByte : bytes) {
            s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return s.toString().equals(encryptedPassword);
    }


}
