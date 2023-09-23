package command;

import dao.Repository;
import model.Show;
import model.Ticket;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Marcus LEOW
 */
public class CancelCommandTest {

    private Repository repository;

    private static String phoneNumber = "99998888";

    private static String showNumber = "test-001";

    @BeforeEach
    void setup() {
        repository = new Repository();
        TestHelper.setupOutputStream();
    }

    @AfterEach
    void restoreDefault() {
        TestHelper.clearOutputStream();
        TestHelper.restoreOutputStream();
    }

    @Test
    void canCancelWithinWindow() throws IOException {
        //Create show
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        Show createdShow = repository.getShowByShowNumber(showNumber);

        //Book show
        TestHelper.bookShow(repository, showNumber, phoneNumber, "A1");
        String consoleOutput = TestHelper.getOutputStreamString();
        Pattern pattern = Pattern.compile("Ticket Number: (.*)");
        Matcher matcher = pattern.matcher(consoleOutput);
        String ticketNumber = "";
        if (matcher.find()) {
            ticketNumber = matcher.group(1);
        }
        Ticket createdTicket = repository.getTicketByPhoneNumberAndBookingNumber(phoneNumber, ticketNumber);
        if (createdTicket == null) {
            Assertions.fail("Ticket was not created/Show was not booked");
        }

        //Cancel booking just 1 minute before the window closes
        LocalDateTime mockedTime = LocalDateTime.of(
                createdTicket.getBookedDateTime().toLocalDate(),
                createdTicket.getBookedDateTime().toLocalTime()
        ).plusMinutes(createdShow.getCancellationWindow() - 1);
        CancelCommand.builder()
                .userInputTicketNumber(ticketNumber)
                .localDateTime(mockedTime)
                .phoneNumber(phoneNumber)
                .build()
                .run(repository);

        //Get the ticket with same number and phone number
        Ticket testedTicket = repository.getTicketByPhoneNumberAndBookingNumber(phoneNumber, ticketNumber);
        assertNull(testedTicket);
    }

    @Test
    void cannotCancelOutsideWindow() throws IOException {
        //Create show
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        Show createdShow = repository.getShowByShowNumber(showNumber);

        //Book show
        TestHelper.bookShow(repository, showNumber, phoneNumber, "A1");
        String consoleOutput = TestHelper.getOutputStreamString();
        Pattern pattern = Pattern.compile("Ticket Number: (.*)");
        Matcher matcher = pattern.matcher(consoleOutput);
        String ticketNumber = "";
        if (matcher.find()) {
            ticketNumber = matcher.group(1);
        }
        Ticket createdTicket = repository.getTicketByPhoneNumberAndBookingNumber(phoneNumber, ticketNumber);
        if (createdTicket == null) {
            Assertions.fail("Ticket was not created/Show was not booked");
        }

        //Cancel booking after show's cancellation window has closed
        LocalDateTime mockedTime = LocalDateTime.of(
                createdTicket.getBookedDateTime().toLocalDate(),
                createdTicket.getBookedDateTime().toLocalTime()
        ).plusMinutes(createdShow.getCancellationWindow());
        CancelCommand.builder()
                .userInputTicketNumber(ticketNumber)
                .localDateTime(mockedTime)
                .phoneNumber(phoneNumber)
                .build()
                .run(repository);

        //Get the ticket with same number and phone number
        Ticket testedTicket = repository.getTicketByPhoneNumberAndBookingNumber(phoneNumber, ticketNumber);
        assertNotNull(testedTicket);
    }

}
