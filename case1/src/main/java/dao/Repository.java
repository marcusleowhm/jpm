package dao;

import model.Show;
import model.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Marcus LEOW
 */
public class Repository {

    private final Map<String, Show> data;

    public Repository() {
        this.data = new HashMap<>();
    }

    //Show access API
    public Show getShowByShowNumber(String showNumber) {
        return this.data.get(showNumber.toLowerCase());
    }
    public Set<Map.Entry<String, Show>> getAllShows() {
        return this.data.entrySet();
    }
    public Integer getShowCount() {
        return this.data.size();
    }
    public Show createNewShow(String showNumber) {
        Show newShow = new Show(showNumber);
        this.data.put(showNumber, newShow);
        return newShow;
    }

    //Ticket access API
    public Ticket createNewTicketInShowWithPhoneNumber(String showNumber, String phoneNumber) {
        Show existingShow = this.data.get(showNumber);
        Ticket newTicket = new Ticket();
        existingShow.putTicket(phoneNumber, newTicket);
        return newTicket;
    }

    public Ticket getTicketByPhoneNumberAndBookingNumber(String phoneNumber, String ticketNumber) {
        for (Map.Entry<String, Show> entry: getAllShows()) {
            Show show = entry.getValue();
            for (Map.Entry<String, Ticket> booking: show.getTickets().entrySet()) {
                String ticketPhoneNumber = booking.getKey();
                Ticket ticket = booking.getValue();
                if (ticketPhoneNumber.equals(phoneNumber) && ticket.getTicketNumber().equals(ticketNumber)) {
                    return ticket;
                }
            }
        }
        return null;
    }

    //For debug
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Show> entry: this.data.entrySet()) {
            sb.append(String.format("%s={\n", entry.getKey()));
            sb.append(String.format("\tcancellationWindow=%s\n", entry.getValue().getCancellationWindow()));
            sb.append("\tseats={\n");

            //Available Seats
            for (String seat: entry.getValue().getAvailableSeats().keySet()) {
                sb.append(String.format("\t\t%s=%s\n", seat, entry.getValue().getAvailableSeats().get(seat)));
            }
            sb.append("\t},\n");

            //Bookings
            if (!entry.getValue().getTickets().isEmpty()) {
                sb.append("\tbookings={\n");
                for (String phoneNumber: entry.getValue().getTickets().keySet()) {
                    sb.append(String.format("\t\t\"%s\"={\n", phoneNumber));
                    Ticket ticket = entry.getValue().getTickets().get(phoneNumber);
                    sb.append(String.format("\t\t\tdateTimeBooked=%s\n", ticket.getBookedDateTime()));
                    sb.append(String.format("\t\t\tticketNumber=\"%s\"\n", ticket.getTicketNumber()));
                    sb.append(String.format("\t\t\tseats=%s\n", ticket.getOccupiedSeats()));
                    sb.append("\t\t},\n");
                }
            } else {
                sb.append("}, \n");
            }
        }
        return sb.toString();
    }


}
