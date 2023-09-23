package command;

import dao.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static command.ListCommand.NOW_SHOWS_CONFIGURED;
import static org.junit.jupiter.api.Assertions.*;

class ListCommandTest {

    private Repository repository;

    @BeforeEach
    void setUp() {
        repository = new Repository();
        TestHelper.setupOutputStream();
    }

    @AfterEach
    void tearDown() {
        TestHelper.clearOutputStream();
        TestHelper.restoreOutputStream();
    }

    @Test
    void shouldDisplayMessageIfNoShow() throws IOException {
        new ListCommand().run(repository);
        assertEquals(NOW_SHOWS_CONFIGURED, TestHelper.getOutputStreamString());
    }

    @Test
    void shouldDisplayListOfShows() throws IOException {
        TestHelper.createValidShows(3, repository);
        TestHelper.clearOutputStream();

        new ListCommand().run(repository);

        StringBuilder sb = new StringBuilder();
        String[] output = TestHelper.getOutputStreamString().split("\n");
        for (int i = 0; i <= 2; i++) {
            sb.append(output[i]);
            sb.append("\n");
        }

        assertEquals("- Show Number: test-001\n" +
                "- Show Number: test-002\n" +
                "- Show Number: test-003\n", sb.toString()
        );

    }

}