package command;

import dao.Repository;
import model.Show;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.TestHelper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SetupCommandTest {

    private Repository repository;

    @BeforeEach
    void createRepository() {
        repository = new Repository();
    }

    @Test
    void validInputShouldCreateShow() {
        TestHelper.createValidShows(1, repository);
        Show show = repository.getShowByShowNumber("test-001");
        assertNotNull(show);
    }

    @Test
    void duplicateShowNumberShouldNotCreateShow() {
        TestHelper.createDuplicateShow(repository);
        Integer showCount = repository.getShowCount();
        Assertions.assertNotEquals(2, showCount);
    }

}