import dao.Repository;
import ui.UserInterface;

/**
 * @author Marcus LEOW
 */
public class BookingApplication {
    public static void main(String[] args) {
        Repository repository = new Repository();
        UserInterface.runMainLoop(repository);
    }
}
