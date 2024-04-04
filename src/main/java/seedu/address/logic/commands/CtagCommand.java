package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;


/**
 * Adds a tag to the address book.
 */
public class CtagCommand extends Command {

    public static final String COMMAND_WORD = "ctag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag or event tag to the address book. "
            + "Parameters: "
            + "tagName\n"
            + "Example: " + COMMAND_WORD + " "
            + "FRIEND\n"
            + "or " + COMMAND_WORD + " " + PREFIX_EVENT_TAG + " eventName" + " "
            + PREFIX_DESCRIPTION + " Description" + " "
            + PREFIX_START_DATE + "yyyy-MM-dd HH:mm:ss" + " "
            + PREFIX_END_DATE + "yyyy-MM-dd HH:mm:ss" + " "
            + "Example: " + COMMAND_WORD + " ";

    public static final String MESSAGE_SUCCESS_TAG = "New tag added: %1$s";
    public static final String MESSAGE_SUCCESS_EVENT_TAG = "New event tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This tag already exists in the address book";
    public static final String MESSAGE_DUPLICATE_EVENT_TAG = "This EventTag already exists in the address book";
    public static final String MESSAGE_FAILED = "Failed to add any tag or event tag.";
    private final Tag toAdd;
    private final EventTag toAddEvent;

    /**
     * Creates an CtagCommand to add the specified {@code Person}
     */
    public CtagCommand(Tag toAdd, EventTag toAddEvent) {
        this.toAdd = toAdd;
        this.toAddEvent = toAddEvent;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean isEventTag = toAddEvent != null;
        boolean isTag = toAdd != null;

        if (isTag) {
            if (model.hasTag(toAdd)) {
                throw new CommandException(MESSAGE_DUPLICATE_TAG);
            }
            model.addTag(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS_TAG, toAdd));
        }

        if (isEventTag) {
            if (model.hasEventTag(toAddEvent.tagName)) {
                throw new CommandException(MESSAGE_DUPLICATE_EVENT_TAG);
            }
            model.addEventTag(toAddEvent);
            return new CommandResult(String.format(MESSAGE_SUCCESS_EVENT_TAG, toAddEvent));
        }
        return new CommandResult(MESSAGE_FAILED);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CtagCommand)) {
            return false;
        }

        CtagCommand e = (CtagCommand) other;
        return toAdd.equals(e.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
