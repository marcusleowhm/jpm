package constant;

/**
 * @author Marcus LEOW
 */
public class ApplicationConstants {

    public static final String INVALID_INPUTS = "Invalid command entered";
    public static final String USAGE = "Usage: <command> <parameters>";

    public static final String SETUP_MESSAGE = "Setup the seats per show";
    public static final String SETUP_INPUT_MESSAGE = "Invalid number of inputs entered for 'setup'";
    public static final String SETUP_ROW_INPUT_MESSAGE = "Rows must be between 1 and 26";
    public static final String SETUP_SEAT_INPUT_MESSAGE = "Seat must be between 1 and 10";
    public static final String SETUP_COMMAND = "- setup <show number> <number of rows> <seats per row> <cancel window in minutes>\n";
    public static final String VIEW_MESSAGE = "Display show number, ticket number, buyer's phone number, seat allocated to buyer";
    public static final String VIEW_INPUT_MESSAGE = "Invalid number of inputs entered for 'view'";
    public static final String VIEW_COMMAND = "- view <show number>\n";

    public static final String AVAILABILITY_MESSAGE = "List all available seat numbers for a show (e.g. A1, F4)";
    public static final String AVAILABILITY_INPUT_MESSAGE = "Invalid number of inputs entered for 'availability'";
    public static final String AVAILABILITY_COMMAND = "- availability <show number>\n";
    public static final String BOOK_MESSAGE = "Book a ticket";
    public static final String BOOK_INPUT_MESSAGE = "Invalid number of inputs entered for 'book'";
    public static final String BOOK_COMMAND = "- book <show number> <phone number> <comma separated list of seats>\n";
    public static final String CANCEL_MESSAGE = "Cancel a ticket";
    public static final String CANCEL_INPUT_MESSAGE = "Invalid number of inputs entered for 'book'";
    public static final String CANCEL_COMMAND = "- cancel <ticket number> <phone number>"; //No need line break (last command)
    public static final String LIST_MESSAGE = "List all show numbers (bonus command to see what show numbers have been added)";
    public static final String LIST_COMMAND = "- list\n";
}
