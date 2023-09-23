package command;

import dao.Repository;
import lombok.Builder;
import model.Show;

/**
 * @author Marcus LEOW
 */
@Builder
public class ViewCommand implements Executable {

    public static final String SHOW_DOES_NOT_EXISTS = "Show Number \"%s\" does not exists\n";
    public ViewCommand(String userInputShowNumber) {
        this.userInputShowNumber = userInputShowNumber;
    }
    private final String userInputShowNumber;

    @Override
    public void run(Repository repository) {
        Show show = repository.getShowByShowNumber(userInputShowNumber.toLowerCase());
        if (show == null) {
            System.out.printf(SHOW_DOES_NOT_EXISTS, userInputShowNumber.toLowerCase());
            return;
        }
        System.out.println(show);
    }
}
