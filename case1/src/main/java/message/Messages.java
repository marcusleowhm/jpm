package message;

import static constant.ApplicationConstants.*;

/**
 * @author Marcus LEOW
 */
public class Messages {

    public static void printAdminHelp() {
        printMessageHeader();
        printListMessages();
        printSetupMessages();
        printViewMessages();
    }

    public static void printUserHelp() {
        printMessageHeader();
        printListMessages();
        printAvailabilityMessages();
        printBookMessages();
        printCancelMessages();
    }

    public static void printMessageHeader() {
        System.out.println(USAGE);
    }

    public static void printSetupMessages() {
        System.out.println(SETUP_MESSAGE);
        System.out.println(SETUP_COMMAND);
    }
    public static void printViewMessages() {
        System.out.println(VIEW_MESSAGE);
        System.out.println(VIEW_COMMAND);
    }
    public static void printAvailabilityMessages() {
        System.out.println(AVAILABILITY_MESSAGE);
        System.out.println(AVAILABILITY_COMMAND);
    }
    public static void printBookMessages() {
        System.out.println(BOOK_MESSAGE);
        System.out.println(BOOK_COMMAND);
    }
    public static void printCancelMessages() {
        System.out.println(CANCEL_MESSAGE);
        System.out.println(CANCEL_COMMAND);
    }
    public static void printListMessages() {
        System.out.println(LIST_MESSAGE);
        System.out.println(LIST_COMMAND);
    }

    public static void printInvalidInputs() {
        System.out.println(INVALID_INPUTS);
    }
    public static void printSetupInputMessage() {
        System.out.println(SETUP_INPUT_MESSAGE);
    }
    public static void printSetupRowMessage() {
        System.out.println(SETUP_ROW_INPUT_MESSAGE);
    }
    public static void printSetupSeatMessage() {
        System.out.println(SETUP_SEAT_INPUT_MESSAGE);
    }
    public static void printViewInputMessage() {
        System.out.println(VIEW_INPUT_MESSAGE);
    }
    public static void printAvailabilityInputMessage() {
        System.out.println(AVAILABILITY_INPUT_MESSAGE);
    }
    public static void printBookInputMessage() {
        System.out.println(BOOK_INPUT_MESSAGE);
    }
    public static void printCancelInputMessage() {
        System.out.println(CANCEL_INPUT_MESSAGE);
    }
}
