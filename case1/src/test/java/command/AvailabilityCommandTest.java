package command;

import dao.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import java.io.IOException;

import static command.AvailabilityCommand.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Marcus LEOW
 */
public class AvailabilityCommandTest {

    private Repository repository;

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
    void shouldDisplayMessageIfShowNumberNotFound() throws IOException {
        AvailabilityCommand.builder()
                .userInputShowNumber("test-001")
                .build()
                .run(repository);
        assertEquals(SHOW_DOES_NOT_EXISTS, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldKeepLookingIfWrongShowNumberProvided () throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        AvailabilityCommand.builder()
                .userInputShowNumber("test-100")
                .build()
                .run(repository);
        assertEquals(SHOW_DOES_NOT_EXISTS, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldDisplayShowNumberAndSeatsAvailable() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        AvailabilityCommand.builder()
                .userInputShowNumber("test-001")
                .build()
                .run(repository);

        String displayMessage = String.format(SHOW_AVAILABLE, "test-001") + SEATS_AVAILABLE;
        assertTrue(TestHelper.getOutputStreamString().contains(displayMessage));
    }

}
