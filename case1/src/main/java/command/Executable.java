package command;

import dao.Repository;

public interface Executable {
    void run(Repository repository);
}
