package ui;

import command.SetupCommand;
import command.ViewCommand;
import message.Messages;

/**
 * @author Marcus LEOW
 */
public class InputValidator {

    public static boolean isValidSetupCommand(String[] inputs) {
        //<setup> <show number> <number of rows> <number of seats per row> <cancellation window in mins>
        if (inputs.length != 5) {
            Messages.printSetupInputMessage();
            return false;
        }
        //Check input datatype
        int userInputNumOfRows;
        int userInputNumOfSeats;
        try {
            userInputNumOfRows = Integer.parseInt(inputs[2]);
            userInputNumOfSeats = Integer.parseInt(inputs[3]);
            Integer.parseInt(inputs[4]);
        } catch (NumberFormatException ex) {
            Messages.printInvalidInputs();
            return false;
        }

        if (userInputNumOfRows < 1 || userInputNumOfRows > 26) {
            Messages.printSetupRowMessage();
            return false;
        }

        if (userInputNumOfSeats < 1 || userInputNumOfSeats > 10) {
            Messages.printSetupSeatMessage();
            return false;
        }

        return true;
    }

    public static boolean isValidViewCommand(String[] inputs) {
        //<view> <show number>
        if (inputs.length != 2) {
            Messages.printViewInputMessage();
            return false;
        }
        return true;
    }

    public static boolean isValidAvailabilityCommand(String[] inputs) {
        //<availability> <show number>
        if (inputs.length != 2) {
            Messages.printAvailabilityInputMessage();
            return false;
        }
        return true;
    }

    public static boolean isValidBookCommand(String[] inputs) {
        //<book> <show number> <phone #> <comma separated list of seats>
        if (inputs.length != 4) {
            Messages.printBookInputMessage();
            return false;
        }
        return true;
    }

    public static boolean isValidCancelCommand(String[] inputs) {
        //<cancel> <ticket number> <phone number>
        if (inputs.length != 3) {
            Messages.printCancelInputMessage();
            return false;
        }
        return true;
    }

}
