package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.ImportExportSyntax.FIELDS;
import static seedu.address.logic.commands.ImportExportSyntax.NUMBER_OF_FIELDS;
import static seedu.address.logic.commands.ImportExportSyntax.PREFIX_MAP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Imports contacts from csv
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";
    public static final String MESSAGE_SUCCESS = "Contacts from csv have been added!";
    public static final String MESSAGE_FILE_NOT_FOUND = "File could not be found! Check import.csv exists at ./import";
    public static final String MESSAGE_FIELDS_FORMAT_ERROR = "An error occurred while parsing the csv! "
            + "Check the field values!";
    public static final String MESSAGE_VALUES_FORMAT_ERROR = "An error occurred while adding persons to the csv!\n "
            + "The values in the csv are converted to add command format\n"
            + "Make sure the values match the correct format for the add command!\n"
            + "The error with the add command occurred as follows:\n";
    public static final String MESSAGE_EVENTS_FORMAT_ERROR = "An error occurred while parsing the EVENTS/TAGS portion! "
            + "Make sure the first occurrence values match the correct format for the add command!\n"
            + "(See UG for more details)\n"
            + "The error with the command occurred as follows:\n";
    public static final String DEFAULT_PATH = "./import/import.csv";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the Address book with contacts from the "
            + "csv file found at given path."
            + "If path not specified, default path is: " + DEFAULT_PATH + "\n"
            + "Parameters: path to file (prefixed with /f)\n"
            + "Example: " + COMMAND_WORD + " f/" + DEFAULT_PATH + "\n"
            + "Example: " + COMMAND_WORD + " f/";

    private final String path;
    private final List<String> personsToAdd = new ArrayList<>(); // To hold the person data from the csv
    private final List<Command> commands = new ArrayList<>(); // Holds commands to assign and generate eventTags;

    /**
     * @param path of the import.csv file containing contacts to import
     */
    public ImportCommand(String path) {
        requireAllNonNull(path);
        this.path = (path.isEmpty()) ? DEFAULT_PATH : path;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try { // parse the csv file at the path w scanner
            parse(model);
            checkFields(personsToAdd.get(0));
            personsToAdd.remove(0); // remove the fields
            addPersons(model);
            loadEventTags(model);

        } catch (FileNotFoundException e) {
            throw new CommandException(MESSAGE_FILE_NOT_FOUND);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(MESSAGE_FIELDS_FORMAT_ERROR);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Parses the data in the csv into the given list
     * Takes all the values and formats them into add command format
     * e.g. John -> n/John
     *
     */
    private void parse(Model model) throws FileNotFoundException, IndexOutOfBoundsException, CommandException {
        StringBuilder currRow = new StringBuilder(); // To hold the current row data
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter(",|\r?\n");
        int count = 0; // holds an idx to keep track of the current field
        String personName = "";
        Boolean fieldsParsed = false;

        while (sc.hasNext()) {
            String next = sc.next();
            if (count == 0) {
                personName = next;
            }

            if (!next.isEmpty()) { // append the field in the correct format if present
                String formatted = formatFieldValue(next, FIELDS.get(count), personName, fieldsParsed);
                currRow.append(formatted);
            }

            count += 1;

            if (count == NUMBER_OF_FIELDS) { // add the person data to the list once parsed
                currRow.deleteCharAt(currRow.length() - 1); // remove trailing " "
                personsToAdd.add(currRow.toString());
                currRow = new StringBuilder();
                count = 0;
                fieldsParsed = true;
            }
        }
        sc.close();
    }

    /**
     * Formats the given data field into proper command format
     * e.g. John -> n/John
     *
     * @param fieldValue the value to be formatted
     * @param field the field the value belongs to
     * @param personName holds the current persons name
     * @param fieldsParsed checks for whether fields are being parsed or not
     * @return the given field value formatted into command format
     */
    private String formatFieldValue(String fieldValue, String field, String personName, Boolean fieldsParsed)
            throws CommandException {
        final String rmvChar = "\uFEFF";
        if (fieldValue.startsWith(rmvChar)) { // to remove extra potential characters added due to csv format
            fieldValue = fieldValue.substring(1);
        }

        if (field.equalsIgnoreCase("EVENTS") && fieldsParsed) {
            String[] eTags = fieldValue.split("\\|");
            for (String eTag : eTags) {
                handleEventTag(eTag, personName);
            }
            return "";
        }

        String prefix = PREFIX_MAP.get(field);
        if (field.equalsIgnoreCase("TAGS") && fieldsParsed) { // handle case of multiple tags in field
            String[] tags = fieldValue.split("\\|");
            StringBuilder tagString = new StringBuilder();
            for (String tag : tags) {
                tagString.append(prefix).append(tag).append(" ");
            }
            return tagString.toString();
        }

        return prefix + fieldValue + " ";
    }

    /**
     * Manages the eventTags to create and assign
     *
     * @param details holds the eventTag details
     */
    private void handleEventTag(String details, String personName) throws CommandException {
        if (!details.equals("N/A") && !details.equalsIgnoreCase("EVENTS")) {
            try {
                AddressBookParser addressBookParser = new AddressBookParser();
                if (details.trim().split(" ").length != 1) { // case where event is defined
                    commands.add(addressBookParser.parseCommand("ctag " + details));
                    String eventName = details.trim().split(" ")[0].substring(2);
                    commands.add(addressBookParser.parseCommand("assign " + personName + "t/" + eventName));
                } else { // case where only event name is given
                    commands.add(addressBookParser.parseCommand("assign " + personName + "t/E-" + details));
                }
            } catch (ParseException e) {
                throw new CommandException(MESSAGE_EVENTS_FORMAT_ERROR + e.getMessage());
            }
        }
    }

    private void loadEventTags(Model model) throws CommandException {
        try {
            AddressBookParser addressBookParser = new AddressBookParser();
            // access model and add/assign events into address book
            for (Command command : commands) {
                command.execute(model);
            }
        } catch (CommandException e) {
            throw new CommandException(MESSAGE_EVENTS_FORMAT_ERROR + e.getMessage());
        }
    }

    /**
     * Returns True if given String matches the number and order of fields specified in FIELDS
     *
     * @param fieldString String parsed from the first row of the csv file
     */
    private void checkFields(String fieldString) throws CommandException {
        StringBuilder fields = new StringBuilder();
        for (String field: FIELDS) {
            fields.append(PREFIX_MAP.get(field));
            fields.append(field).append(" ");
        }
        fields.deleteCharAt(fields.length() - 1); // adjust for extra " "
        if (!fieldString.equalsIgnoreCase(fields.toString())) {
            throw new CommandException(MESSAGE_FIELDS_FORMAT_ERROR);
        }
    }

    /**
     * Adds the person data from the list to the addressbook
     * Simulates the work of AddressBookParser
     *
     * @param model reference to the model to add the data into
     */
    private void addPersons(Model model) throws CommandException {
        try {
            AddressBookParser addressBookParser = new AddressBookParser();
            // access model and add people into address book
            for (String person : personsToAdd) {
                String commandText = "add " + person;
                Command command = addressBookParser.parseCommand(commandText);
                command.execute(model);
            }
        } catch (CommandException | ParseException e) {
            throw new CommandException(MESSAGE_VALUES_FORMAT_ERROR);
        }
    }
}
