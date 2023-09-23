package command;

import dao.Repository;
import lombok.Builder;
import model.Show;


/**
 * @author Marcus LEOW
 */
@Builder
public class SetupCommand implements Executable {

    private final String userInputShowNumber;
    private final int userInputNumOfRows;
    private final int userInputNumOfSeats;
    private final int userInputCancellationWindow;

    @Override
    public void run(Repository repository) {
        if (repository.getShowByShowNumber(userInputShowNumber.toLowerCase()) != null) {
            System.out.printf("Show Number \"%s\" already exists. Please choose another name.\n", userInputShowNumber);
            return;
        }

        //Create new show if pass validation
        Show newShow = repository.createNewShow(userInputShowNumber.toLowerCase());
        newShow.setCancellationWindow(userInputCancellationWindow);
        //Create new seats for new show
        for (int i = 1; i <= userInputNumOfRows; i++) {
            char alphabet = (char) (i + 64);
            for (int j = 1; j <= userInputNumOfSeats; j++) {
                newShow.putAvailableSeat(String.format("%s%s", alphabet, j), true);
            }
        }
        System.out.printf("New show with %s rows and %s seats added\n", userInputNumOfRows, userInputNumOfSeats);
    }
}
