package command;

import dao.Repository;
import lombok.Builder;
import model.Show;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Marcus LEOW
 */
@Builder
public class AvailabilityCommand implements Executable {

    public static final String SHOW_AVAILABLE = "Show Number: %s\n";
    public static final String SHOW_DOES_NOT_EXISTS = "Show Number does not exists";
    public static final String SEATS_AVAILABLE = "Seats available: \n";

    private final String userInputShowNumber;

    @Override
    public void run(Repository repository) {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Show> entry: repository.getAllShows()) {
            Show currentShow = entry.getValue();
            if (currentShow == null) {
                continue;
            }

            if (currentShow.getShowNumber().equalsIgnoreCase(userInputShowNumber)) {
                System.out.printf(SHOW_AVAILABLE, currentShow.getShowNumber());
                LinkedHashMap<String, Boolean> seats = entry.getValue().getAvailableSeats();

                //https://www.rapidtables.com/code/text/ascii-table.html
                int ascii = 65;
                for (String seat: seats.keySet()) {
                    if (seats.get(seat)) {
                        if (seat.charAt(0) != ascii) {
                            sb.append("\n");
                            ascii = seat.charAt(0);
                        }
                        sb.append(String.format("%s, ", seat));
                    }
                }
                System.out.printf(SEATS_AVAILABLE);
                System.out.println(sb.toString().trim());
                return;
            }
        }
        System.out.println(SHOW_DOES_NOT_EXISTS);
    }
}
