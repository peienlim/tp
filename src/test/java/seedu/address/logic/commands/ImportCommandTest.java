package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ImportCommand.
 */
public class ImportCommandTest {

    @Test
    public void execute_invalidFilePath_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new ImportCommand("xxx"), model, ImportCommand.MESSAGE_FILE_NOT_FOUND);
    }

    @Test
    public void execute_invalidFileValues_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new ImportCommand("./src/test/data/ImportTest/invalidValueTest.csv"),
                model, ImportCommand.MESSAGE_VALUES_FORMAT_ERROR
                        + "Tags names should be alphanumeric and not contain spaces");
    }

    @Test
    public void execute_invalidFieldValues_failure() {
        Model model = new ModelManager();

        assertCommandFailure(new ImportCommand("./src/test/data/ImportTest/invalidFieldsTest.csv"),
                model, ImportCommand.MESSAGE_FIELDS_FORMAT_ERROR);
    }

}
