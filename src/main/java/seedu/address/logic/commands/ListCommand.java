package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;

import seedu.address.model.Model;
import seedu.address.model.tag.EventTag;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";

    private static final EventTag DEFAULT_EVENT_TAG = new EventTag("All", "All contacts in EventBook",
            LocalDateTime.parse("2024-04-05T14:00:00"), LocalDateTime.parse("2024-04-05T14:00:00"));

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearCurrentEventTag();
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS, DEFAULT_EVENT_TAG);
    }
}
