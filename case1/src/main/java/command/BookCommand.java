package command;

import dao.Repository;
import lombok.Builder;
import model.Show;
import model.Ticket;

import java.util.Map;

/**
 * @author Marcus LEOW
 */
@Builder
public class BookCommand implements Executable {

    private final String userInputShowNumber;
    private final String userInputPhoneNumber;
    private final String userInputListOfSeats;

    public static final String INVALID_SHOW_NUMBER = "Error: Invalid Show Number entered";
    public static final String ONLY_ONE_ALLOWED = "Error: Only one booking is allowed per phone number per show";
    public static final String INVALID_SEAT_NUMBER = "Error: Invalid seat number detected";
    public static final String SOME_SEATS_UNAVAILABLE = "Error: Some seats are not available";
    public static final String BOOKING_SUCCESSFUL = "Booking made successfully.\n";
    public static final String TICKET_NUMBER = "Ticket Number: ";

    @Override
    public void run(Repository repository) {

        Show selectedShow = repository.getShowByShowNumber(userInputShowNumber.toLowerCase());
        if (selectedShow == null) {
            System.out.println(INVALID_SHOW_NUMBER);
            return;
        }

        if (selectedShow.getTickets().containsKey(userInputPhoneNumber.toLowerCase())) {
            System.out.println(ONLY_ONE_ALLOWED);
            return;
        }

        Map<String, Boolean> seatAvailability =  selectedShow.getAvailableSeats();
        String[] selectedSeats = userInputListOfSeats.trim().toUpperCase().split(",");

        //Check if the all entered seats are available
        for (String seatNumber: selectedSeats) {
            if (seatAvailability.get(seatNumber) == null) {
                System.out.println(INVALID_SEAT_NUMBER);
                return;
            }

            if (!seatAvailability.get(seatNumber)) {
                System.out.println(SOME_SEATS_UNAVAILABLE);
                return;
            }
        }

        Ticket newTicket = repository.createNewTicketInShowWithPhoneNumber(userInputShowNumber.toLowerCase(), userInputPhoneNumber);
        //Create new ticket and update the show's seat availability
        for (String seatNumber: selectedSeats) {
            seatAvailability.put(seatNumber, false);
            newTicket.getOccupiedSeats().add(seatNumber);
        }
        System.out.printf(BOOKING_SUCCESSFUL);
        System.out.printf("%s%s%n", TICKET_NUMBER, newTicket.getTicketNumber());
    }
}
