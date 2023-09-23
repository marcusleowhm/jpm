package command;

import dao.Repository;
import model.Show;

import java.util.Map;

/**
 * @author Marcus LEOW
 */
public class ListCommand implements Executable {

    public static final String NOW_SHOWS_CONFIGURED = "No shows configured";

    @Override
    public void run(Repository repository) {
        if (repository.getShowCount() == 0) {
            System.out.println(NOW_SHOWS_CONFIGURED);
            return;
        }

        for (Map.Entry<String, Show> entry: repository.getAllShows()) {
            System.out.printf("- Show Number: %s\n", entry.getKey());
        }
    }
}
