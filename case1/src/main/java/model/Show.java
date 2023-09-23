package model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Marcus LEOW
 */
public class Show {

    public static final String SHOW_NUMBER = "Show Number: %s%n";
    public static final String HEADER = "Ticket Number\tPhone Number\tSeats Allocated";
    private final String showNumber;
    private int cancellationWindow;

    //String represents seat number
    private final LinkedHashMap<String, Boolean> availableSeats;

    //String represents phone number for easier searching by phone number
    private final Map<String, Ticket> tickets;

    public Show(String showNumber) {
        this.showNumber = showNumber;
        this.availableSeats = new LinkedHashMap<>();
        this.tickets = new HashMap<>();
    }
    public String getShowNumber() {
        return this.showNumber;
    }
    public int getCancellationWindow() {
        return this.cancellationWindow;
    }
    public void setCancellationWindow(int cancellationWindow) {
        this.cancellationWindow = cancellationWindow;
    }
    public LinkedHashMap<String, Boolean> getAvailableSeats() {
        return this.availableSeats;
    }
    public void putAvailableSeat(String seatNumber, Boolean isAvailable) {
        this.availableSeats.put(seatNumber, isAvailable);
    }
    public Map<String, Ticket> getTickets() {
        return this.tickets;
    }
    public void putTicket(String phoneNumber, Ticket newTicket) {
        this.tickets.put(phoneNumber, newTicket);
    }

    //Used in the View Command
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(SHOW_NUMBER, this.showNumber.toLowerCase()));
        sb.append(HEADER);
        for (Map.Entry<String, Ticket> entry: this.tickets.entrySet()) {
            sb.append(String.format("\n%s\t%s\t%s",
                    entry.getValue().getTicketNumber(),
                    entry.getKey(),
                    entry.getValue().getOccupiedSeats()
                    ));
        }
        return sb.toString();
    }

}
