package ui;

import org.junit.jupiter.api.Test;
import utility.InputValidator;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

    private final String showNumber = "TEST-001";
    private final String phoneNumber = "9876-1234";
    @Test
    void setupInvalidNumberOfInputsShouldNotBeAccepted() {
        //command is missing the row/seat/cancellation window
        String command = String.format("setup %s 5 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupInvalidTypeOfInputShouldNotBeAccepted() {
        //command contains words in place of a number
        String command = String.format("setup %s five 5 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupZeroRowShouldNotBeAccepted() {
        //command contains row = 0
        String command = String.format("setup %s 0 5 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupZeroSeatShouldNotBeAccepted() {
        //command contains seat = 0
        String command = String.format("setup %s 5 0 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupMoreThanTenSeatShouldNotBeAccepted() {
        //command contains seat = 11, which is not allowed (constraint <= 10)
        String command = String.format("setup %s 5 11 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupMoreThanTwentySixRowShouldNotCreateShow() {
        //command contains seat = 27, which is not allowed (constraint <= 26)
        String command = String.format("setup %s 27 5 10", showNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void setupValidInputShouldBeAccepted() {
        //command is valid
        String command = String.format("setup %s 5 5 10", showNumber);
        String[] inputs = command.split(" ");
        assertTrue(InputValidator.isValidSetupCommand(inputs));
    }

    @Test
    void viewInvalidNumberOfInputsShouldNotBeAccepted() {
        //command is missing parameters
        String command = "view";
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidViewCommand(inputs));
    }

    @Test
    void viewValidNumberOfInputsShouldBeAccepted() {
        //command is valid
        String command = String.format("view %s", showNumber);
        String[] inputs = command.split(" ");
        assertTrue(InputValidator.isValidViewCommand(inputs));
    }

    @Test
    void availabilityInvalidNumberOfInputsShouldNotBeAccepted() {
        //command is missing parameters
        String command = "availability";
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidAvailabilityCommand(inputs));
    }

    @Test
    void availabilityValidNumberOfInputsShouldBeAccepted() {
        //command is valid
        String command = String.format("availability %s", showNumber);
        String[] inputs = command.split(" ");
        assertTrue(InputValidator.isValidAvailabilityCommand(inputs));
    }

    @Test
    void bookInvalidNumberOfInputsShouldNotBeAccepted() {
        //command is missing parameters
        String command = "book";
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidBookCommand(inputs));
    }

    @Test
    void bookValidNumberOfInputsShouldBeAccepted() {
        //command is valid
        String command = String.format("book %s %s A1,A2,A3", phoneNumber, showNumber);
        String[] inputs = command.split(" ");
        assertTrue(InputValidator.isValidBookCommand(inputs));
    }

    @Test
    void cancelInvalidNumberOfInputsShouldNotBeAccepted() {
        //command is missing parameters
        String command = String.format("cancel %s", phoneNumber);
        String[] inputs = command.split(" ");
        assertFalse(InputValidator.isValidCancelCommand(inputs));
    }

    @Test
    void cancelValidNumberOfInputsShouldBeAccepted() {
        //command is valid
        String command = String.format("cancel ticket %s", phoneNumber);
        String[] inputs = command.split(" ");
        assertTrue(InputValidator.isValidCancelCommand(inputs));
    }
}