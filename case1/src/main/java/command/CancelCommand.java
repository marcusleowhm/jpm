package command;

import dao.Repository;
import lombok.Builder;
import model.Show;
import model.Ticket;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Marcus LEOW
 */
@Builder
public class CancelCommand implements Executable {

    private final String userInputTicketNumber;
    private final String phoneNumber;
    private final LocalDateTime localDateTime;

    @Override
    public void run(Repository repository) {

        for (Map.Entry<String, Show> entry: repository.getAllShows()) {
            Show currentShow = entry.getValue();

//            //If there is no show at all
//            if (currentShow == null) {
//                continue;
//            }

            int showCancelWindow = entry.getValue().getCancellationWindow();

            Map<String, Ticket> tickets = currentShow.getTickets();
            Ticket currentTicket = tickets.get(phoneNumber);
            //if currentShow does not contain given ticket number
//            if (currentTicket == null) {
//                continue;
//            }
            String currentTicketNumber = currentTicket.getTicketNumber();

            //If a ticket matches the ticket number provided
            if (currentTicketNumber.equalsIgnoreCase(userInputTicketNumber)) {
                //If the time is still within the window
                if (currentTicket.getBookedDateTime().plusMinutes(showCancelWindow).isAfter(localDateTime)) {
                    List<String> seatsToCancel = currentTicket.getOccupiedSeats();
                    for (String seat: seatsToCancel) {
                        currentShow.putAvailableSeat(seat, true);
                    }
                    tickets.remove(phoneNumber);
                    System.out.printf("Cancelled ticket %s%n", currentTicketNumber);
                    System.out.printf("Freeing up seats: %s%n", seatsToCancel);
                }
                else {
                    System.out.println("Error: Too much time has elapsed to cancel the ticket");
                }
                return;
            }
        }
        System.out.println("Ticket and/or phone number not found");
    }
}
