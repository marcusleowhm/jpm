package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Marcus LEOW
 */
public class Ticket {

    LocalDateTime bookedDateTime;
    String ticketNumber;
    List<String> occupiedSeats;

    public Ticket() {
        this.bookedDateTime = LocalDateTime.now();
        this.ticketNumber = UUID.randomUUID().toString();
        this.occupiedSeats = new ArrayList<>();
    }

    public LocalDateTime getBookedDateTime() {
        return this.bookedDateTime;
    }
    public String getTicketNumber() {
        return this.ticketNumber;
    }
    public List<String> getOccupiedSeats() {
        return this.occupiedSeats;
    }

}
