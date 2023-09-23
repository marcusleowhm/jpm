package command;

import dao.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static command.ViewCommand.SHOW_DOES_NOT_EXISTS;
import static model.Show.HEADER;
import static model.Show.SHOW_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ViewCommandTest {

    private Repository repository;
    private final String showNumber = "test-001";

    @AfterEach
    void tearDown() {
        TestHelper.clearOutputStream();
        TestHelper.restoreOutputStream();
    }

    @BeforeEach
    void setUp() {
        repository = new Repository();
        TestHelper.setupOutputStream();
    }

    @Test
    void shouldDisplayMessageIfNoShow() throws IOException {
        ViewCommand.builder().userInputShowNumber(showNumber).build().run(repository);
        assertEquals(String.format(SHOW_DOES_NOT_EXISTS, showNumber.toLowerCase()).trim(), TestHelper.getOutputStreamString());
    }

    @Test
    void shouldDisplayShow() throws IOException {
        TestHelper.createValidShows(1, repository);
        TestHelper.clearOutputStream();

        ViewCommand.builder().userInputShowNumber(showNumber).build().run(repository);
        String showNumberString = String.format(SHOW_NUMBER, showNumber.toLowerCase());

        String displayMessage = String.format("%s%s", showNumberString, HEADER).trim();
        assertTrue(TestHelper.getOutputStreamString().contains(displayMessage));
    }
}