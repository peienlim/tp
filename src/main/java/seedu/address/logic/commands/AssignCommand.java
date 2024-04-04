package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Assigns a person from the address book with the given tags.
 */
public class AssignCommand extends Command {

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns the person identified by the index number or full name used in the displayed person list with"
            + " the given tags.\n"
            + "Parameters: INDEX (must be a positive integer) or NAME (must be the exact full name), TAGS (t/TAGNAME)\n"
            + "Examples: " + COMMAND_WORD + " 1" + "t/friends"
            + " or" + COMMAND_WORD + " John Doe" + "t/friends"
            + " or" + COMMAND_WORD + " 1" + "t/E-eventName";

    public static final String MESSAGE_ASSIGN_PERSON_MISSING = "Please provide either a name or index to delete.";
    public static final String MESSAGE_ASSIGN_TAG_MISSING = "Please ensure that all tag(s) provided exist.";
    public static final String MESSAGE_ASSIGN_PERSON_SUCCESS = "Assigned Person: %1$s";
    private final Index targetIndex;
    private final String targetName;
    private final Set<Tag> targetTagList;
    private final Set<Tag> targetEventTagList;

    /**
     * Constructs an AssignCommand with the specified target index, target name and target tag list.
     *
     * @param targetIndex The index of the person to be deleted.
     * @param targetName The name of the person to be deleted.
     * @param targetTagList The set of tags to be added to the person.
     */
    public AssignCommand(Index targetIndex, String targetName, Set<Tag> targetTagList, Set<Tag> targetEventTagList) {
        this.targetIndex = targetIndex;
        this.targetName = targetName;
        this.targetTagList = targetTagList;
        this.targetEventTagList = targetEventTagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String dummyName = " ";

        for (Tag t : targetTagList) {
            if (!model.hasTag(t)) {
                throw new CommandException(MESSAGE_ASSIGN_TAG_MISSING);
            }
        }

        for (Tag t : targetEventTagList) {
            if (!model.hasEventTag(t.tagName)) {
                throw new CommandException(MESSAGE_ASSIGN_TAG_MISSING);
            }
        }

        if (targetIndex != null) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToAssign = lastShownList.get(targetIndex.getZeroBased());
            model.assign(personToAssign, targetTagList, targetEventTagList);
            return new CommandResult(String.format(MESSAGE_ASSIGN_PERSON_SUCCESS, Messages.format(personToAssign)));

        } else if (targetName != dummyName) {
            Optional<Person> personToFind = lastShownList.stream().filter(person -> person.getName()
                    .toString().equals(targetName)).findFirst();
            if (personToFind.isPresent()) {
                Person personToAssign = personToFind.get();
                model.assign(personToAssign, targetTagList, targetEventTagList);
                return new CommandResult(String.format(MESSAGE_ASSIGN_PERSON_SUCCESS, Messages.format(personToAssign)));
            } else {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
            }
        } else {
            throw new CommandException(MESSAGE_ASSIGN_PERSON_MISSING);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignCommand)) {
            return false;
        }

        AssignCommand otherAssignCommand = (AssignCommand) other;
        return (targetIndex.equals(otherAssignCommand.targetIndex)
                && targetTagList.equals(otherAssignCommand.targetTagList)
                && targetEventTagList.equals(otherAssignCommand.targetEventTagList)
                && targetName.equals((otherAssignCommand.targetName)));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("targetName", targetName)
                .add("targetTagList", targetTagList)
                .add("targetTagList", targetEventTagList)
                .toString();
    }
}
