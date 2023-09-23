package command;

import dao.Repository;
import model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static command.BookCommand.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Marcus LEOW
 */
public class BookCommandTest {

    private Repository repository;
    private static String phoneNumber = "99998888";
    private static String secondPhoneNumber = "88889999";
    private static String validTestInputSeats = "A1,A4,B4,C3";
    private static String invalidTestInputSeats = "A1,A4,B4,H6";

    @BeforeEach
    void setup() {
        repository = new Repository();
        TestHelper.setupOutputStream();
    }

    @AfterEach
    void restoreDefault() {
        TestHelper.restoreOutputStream();
    }

    @Test
    void canBookValidShow() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.bookShow(repository, "test-001", "99998888", validTestInputSeats);

        String consoleOutput = TestHelper.getOutputStreamString();
        Pattern pattern = Pattern.compile("Ticket Number: (.*)");
        Matcher matcher = pattern.matcher(consoleOutput);
        String ticketNumber = "";
        if (matcher.find()) {
            ticketNumber = matcher.group(1);
        }

        Ticket createdTicket = repository.getTicketByPhoneNumberAndBookingNumber("99998888", ticketNumber);
        assertNotNull(createdTicket);
    }

    @Test
    void shouldNotBookNonexistentShowNumber() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-100")
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats(validTestInputSeats)
                .build()
                .run(repository);
        assertEquals(INVALID_SHOW_NUMBER, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldNotBookValidShowWithInvalidSeats() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-001")
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats(invalidTestInputSeats)
                .build()
                .run(repository);
        assertEquals(INVALID_SEAT_NUMBER, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldNotBookValidShowWithUnavailableSeats() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-001")
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats(validTestInputSeats)
                .build()
                .run(repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-001")
                .userInputPhoneNumber(secondPhoneNumber)
                .userInputListOfSeats("A1")
                .build()
                .run(repository);
        assertEquals(SOME_SEATS_UNAVAILABLE, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldNotBookSameShowWithSamePhoneNumber() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-001")
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats(validTestInputSeats)
                .build()
                .run(repository);
        TestHelper.clearOutputStream();

        BookCommand.builder()
                .userInputShowNumber("test-001")
                .userInputPhoneNumber(phoneNumber)
                .userInputListOfSeats("C1")
                .build()
                .run(repository);
        assertEquals(ONLY_ONE_ALLOWED, TestHelper.getOutputStreamString());
    }
}
