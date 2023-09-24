package ui;

import command.*;
import dao.Repository;
import message.Messages;
import utility.InputValidator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Marcus LEOW
 */
public class UserInterface {

    public static void runMainLoop(Repository repo) throws NoSuchElementException {

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("──────────────────────────────────────────────────────────────────────────");
            System.out.println("Type 'help' to list the commands. Type 'exit' to exit the program.");
            System.out.print("> ");

            String line = sc.nextLine();

            //Trim extra white space between params
            //Split line into respective params
            String[] inputs = line.trim().replaceAll("\\s{2,}"," ").split(" ");

            String command = inputs[0].toLowerCase();
            if (command.equals("exit")) {
                break;
            }

            if (command.equals("list")) {
                new ListCommand().run(repo);
                continue;
            }

            if (command.equals("availability")) {
                if (InputValidator.isValidAvailabilityCommand(inputs)) {
                    AvailabilityCommand executor = AvailabilityCommand.builder()
                            .userInputShowNumber(inputs[1])
                            .build();
                    executor.run(repo);
                }
                continue;
            }

            if (command.equals("book")) {
                if (InputValidator.isValidBookCommand(inputs)) {
                    BookCommand executor = BookCommand.builder()
                            .userInputShowNumber(inputs[1])
                            .userInputPhoneNumber(inputs[2])
                            .userInputListOfSeats(inputs[3])
                            .build();
                    executor.run(repo);
                }
                continue;
            }

            if (command.equals("cancel")) {
                if (InputValidator.isValidCancelCommand(inputs)) {
                    LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
                    CancelCommand executor = CancelCommand.builder()
                            .userInputTicketNumber(inputs[1])
                            .phoneNumber(inputs[2])
                            .localDateTime(localDateTime)
                            .build();
                    executor.run(repo);
                }
                continue;
            }

            if (command.equals("help")) {
                Messages.printUserHelp();
                continue;
            }

            if (command.equals("enter-godmode")) {
                AdminUserInterface.runMainLoop(repo);
                continue;
            }

            Messages.printInvalidInputs();
        }
    }

}
