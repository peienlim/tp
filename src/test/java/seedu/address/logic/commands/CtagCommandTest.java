package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CtagCommand.MESSAGE_SUCCESS_EVENT_TAG;
import static seedu.address.logic.commands.CtagCommand.MESSAGE_SUCCESS_TAG;
import static seedu.address.testutil.TypicalPersons.getTypicalEventBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;
//
public class CtagCommandTest {
    private Model model = new ModelManager(getTypicalEventBook(), new UserPrefs());
    private Tag tag = new Tag("dummy");
    private Tag tag2 = new Tag("dummy2");
    private EventTag eTag = new EventTag("dummy",
            "description",
            LocalDateTime.of(2001, 1, 1, 1, 1, 1),
            LocalDateTime.of(2001, 1, 1, 1, 1, 1));
    private EventTag eTag2 = new EventTag("dummy",
            "description",
            LocalDateTime.of(2001, 1, 1, 1, 1, 1),
            LocalDateTime.of(2001, 1, 1, 1, 1, 1));

    @Test
    public void constructor_cTagInvalid_throwsFailedMessage() {
        CtagCommand dummy = new CtagCommand(null, null);
        try {
            assertEquals(new CommandResult(CtagCommand.MESSAGE_FAILED), dummy.execute(model));
        } catch (CommandException e) {
            assertEquals(1, 0);
        }
    }

    @Test
    public void execute_testEquals_throwsSuccess() {
        CtagCommand tagTag = new CtagCommand(tag, null);
        CtagCommand eventTag = new CtagCommand(null, eTag);

        assertTrue(tagTag.equals(tagTag));
        assertTrue(eventTag.equals(eventTag));

        CtagCommand tagTagCopy = new CtagCommand(tag, null);
        CtagCommand eTagCopy = new CtagCommand(null, eTag);
        assertTrue(tagTag.equals(tagTagCopy));
        assertTrue(eventTag.equals(eTagCopy));

        assertFalse(tagTag.equals(new CtagCommand(tag2, null)));
        assertTrue(eventTag.equals(new CtagCommand(null, eTag2)));

        assertFalse(tagTag.equals(1));
        assertFalse(eventTag.equals(1));
    }

    @Test
    public void execute_cTagInvalid_throwsFailedMessage() {
        try {
            CtagCommand dOne = new CtagCommand(tag, null);
            CommandResult r = dOne.execute(model);
            assertEquals(String.format(MESSAGE_SUCCESS_TAG, tag), r.getFeedbackToUser());

            CtagCommand dTwo = new CtagCommand(null, eTag);
            CommandResult r2 = dTwo.execute(model);
            assertEquals(String.format(MESSAGE_SUCCESS_EVENT_TAG, eTag), r2.getFeedbackToUser());
        } catch (Exception e) {
            assertEquals(1, 0);
        }
    }
}
