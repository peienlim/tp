package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.ImportExportSyntax.FIELDS;
import static seedu.address.logic.commands.ImportExportSyntax.NUMBER_OF_FIELDS;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Exports contacts from address book
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String MESSAGE_SUCCESS = "Contacts from address book have been exported!";
    public static final String MESSAGE_FAILURE = "Something went wrong! Make sure export.csv is not open!";
    public static final String EXPORT_PATH = "./export/export.csv";


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        exportFile(model);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Reads the current address book and exports to ./export/export.csv
     *
     * @param model as in execute()
     * @throws CommandException in the case that the file cannot be written to
     */
    private void exportFile(Model model) throws CommandException {
        ObservableList<Person> list = model.getFilteredPersonList();

        try {
            File exportFile = new File(EXPORT_PATH);
            FileWriter fw = new FileWriter(exportFile);

            for (int i = 0; i < NUMBER_OF_FIELDS; i++) {
                fw.write(FIELDS.get(i));
                if (i + 1 != NUMBER_OF_FIELDS) {
                    fw.write(",");
                }
            }
            fw.write("\n");

            for (Person person : list) {
                fw.write(person.getName().toString());
                fw.write(",");
                fw.write(person.getPhone().toString());
                fw.write(",");
                fw.write(person.getEmail().toString());
                fw.write(",");
                fw.write(person.getAddress().toString());
                fw.write(",");
                fw.write(""); //placeholder for tag
                fw.write("\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new CommandException(MESSAGE_FAILURE);
        }
    }
}
