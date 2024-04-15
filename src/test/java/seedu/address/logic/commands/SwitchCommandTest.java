package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.SwitchCommand.MESSAGE_NO_EVENT_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

public class SwitchCommandTest {

    private static final String TAG_NAME = "Meeting";
    private static final String DESCRIPTION = "Discuss project";
    private static final LocalDateTime START_DATE = LocalDateTime.of(2022, 3, 15, 10, 0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2022, 3, 15, 12, 0);

    private ModelStubWithEventTag modelStub = new ModelStubWithEventTag();

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SwitchCommand(null));
    }

    @Test
    public void execute_validTag_success() throws Exception {
        SwitchCommand switchCommand = new SwitchCommand(new Tag(TAG_NAME));
        CommandResult commandResult = switchCommand.execute(modelStub);
        assertEquals(String.format(SwitchCommand.MESSAGE_SUCCESS_EVENT_TAG + "%s\n"
                        + "Description: %s\n"
                        + "Start Date: %s\n"
                        + "End Date: %s\n", TAG_NAME, DESCRIPTION,
                START_DATE.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm a")),
                END_DATE.format(DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm a"))),
                    commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_noEventTag_throwsCommandException() {
        SwitchCommand switchCommand = new SwitchCommand(new Tag("Nonexistent"));
        assertThrows(CommandException.class, MESSAGE_NO_EVENT_TAG, () -> switchCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag meetingTag = new Tag(TAG_NAME);
        Tag discussionTag = new Tag("Discussion");

        SwitchCommand switchCommandWithMeetingTag = new SwitchCommand(meetingTag);
        SwitchCommand switchCommandWithDiscussionTag = new SwitchCommand(discussionTag);

        // same object -> returns true
        assertTrue(switchCommandWithMeetingTag.equals(switchCommandWithMeetingTag));

        // same values -> returns true
        SwitchCommand switchCommandWithMeetingTagCopy = new SwitchCommand(meetingTag);
        assertTrue(switchCommandWithMeetingTag.equals(switchCommandWithMeetingTagCopy));

        // different types -> returns false
        assertFalse(switchCommandWithMeetingTag.equals(1));

        // null -> returns false
        assertFalse(switchCommandWithMeetingTag.equals(null));

        // different tag -> returns false
        assertFalse(switchCommandWithMeetingTag.equals(switchCommandWithDiscussionTag));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getEventBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEventBook(ReadOnlyEventBook eventBook) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyEventBook getEventBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void assign(Person targetPerson, Set<Tag> tags, Set<Tag> eventTags) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEventTag(EventTag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEventTag(String tagName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEventTag(EventTag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEventTag(EventTag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public EventTag getEventTag(String tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableSet<EventTag> getEventTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateEventTagPersonList(EventTag t) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentEventTag(EventTag tagName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearCurrentEventTag() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTagPersonList(Tag t) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains an event tag.
     */
    private class ModelStubWithEventTag extends ModelStub {
        private final EventTag eventTag = new EventTag(TAG_NAME, DESCRIPTION, START_DATE, END_DATE);

        @Override
        public boolean hasEventTag(String tagName) {
            return TAG_NAME.equals(tagName);
        }

        @Override
        public EventTag getEventTag(String tagName) {
            return eventTag;
        }

        @Override
        public void updateEventTagPersonList(EventTag t) {
            // Do nothing
        }

    }
}

