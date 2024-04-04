package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.EventTag;

/**
 * Format devent command for EventBook that deletes a tag.
 */
public class DeventCommand extends Command {

    public static final String COMMAND_WORD = "devent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": delete an event tag from the list\n"
            + "Parameters: "
            + "EventTag name\n"
            + "Example: " + COMMAND_WORD + " "
            + "FRIEND\n";

    public static final String MESSAGE_SUCCESS_DELETE = "Deleted event tag %1$s";
    public static final String MESSAGE_NO_EVENT_TAG = "This EventTag does not exists in the address book";
    public static final String MESSAGE_FAILED = "Failed to remove event tag.";
    private final String tagName;

    /**
     * Creates an CtagCommand to add the specified {@code Person}
     */
    public DeventCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.hasEventTag(tagName)) {
            throw new CommandException(MESSAGE_NO_EVENT_TAG);
        }
        //get event tag to be used for checking
        EventTag et = model.getEventTag(tagName);
        model.clearCurrentEventTag();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateTagPersonList(et);
        model.deleteEventTag(et);
        return new CommandResult(String.format(MESSAGE_SUCCESS_DELETE, et));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.logic.commands.DeventCommand)) {
            return false;
        }

        seedu.address.logic.commands.DeventCommand e = (seedu.address.logic.commands.DeventCommand) other;
        return this.tagName == ((DeventCommand) other).tagName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tagName", tagName)
                .toString();
    }
}

