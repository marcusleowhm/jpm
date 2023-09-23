package utility;

import command.BookCommand;
import command.SetupCommand;
import dao.Repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Marcus LEOW
 */
public class TestHelper {

    private static final String showNumber = "test-00%s";
    private static final int numOfSeats = 5;
    private static final int numOfRows = 5;
    private static final int cancellationWindow = 5;

    private static final PrintStream stdOut = System.out;
    private static final ByteArrayOutputStream os = new ByteArrayOutputStream();

    public static void clearOutputStream() {
        os.reset();
    }

    /**
     * To be called in the method with @BeforeEach to set up output stream
     */
    public static void setupOutputStream() {
        System.setOut(new PrintStream(os));
    }

    /**
     * To be called in the method with @AfterEach to restore the JVM output stream
     */
    public static void restoreOutputStream() {
        System.setOut(stdOut);
    }

    public static String getOutputStreamString() throws IOException {
        String outputString = os.toString().trim();
        clearOutputStream();
        return outputString;
    }

    public static void createValidShows(int count, Repository repo) {
        for (int i = 0; i < count; i++) {
            SetupCommand.builder()
                    .userInputShowNumber(String.format(showNumber, i + 1))
                    .userInputNumOfSeats(numOfSeats)
                    .userInputNumOfRows(numOfRows)
                    .userInputCancellationWindow(cancellationWindow)
                    .build()
                    .run(repo);
        }
    }

    public static void createDuplicateShow(Repository repo) {
        for (int i = 0; i < 2; i++) {
            SetupCommand.builder()
                    .userInputShowNumber("test-001")
                    .userInputNumOfSeats(numOfSeats)
                    .userInputNumOfRows(numOfRows)
                    .userInputCancellationWindow(cancellationWindow)
                    .build()
                    .run(repo);
        }
    }

    public static void bookShow(Repository repo, String showNumber, String phoneNumber, String seatNumbers) {
        BookCommand.builder()
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats(seatNumbers)
                .userInputShowNumber(showNumber)
                .build()
                .run(repo);
    }

}
