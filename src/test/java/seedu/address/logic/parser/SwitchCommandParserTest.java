package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SwitchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

public class SwitchCommandParserTest {

    private final SwitchCommandParser parser = new SwitchCommandParser();

    @Test
    public void parse_validArgs_returnsSwitchCommand() throws ParseException {
        String tagName = "Meeting";
        assertEquals(new SwitchCommand(new Tag(tagName)), parser.parse(tagName));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Test with more than one argument
        assertThrows(ParseException.class, () -> parser.parse("Meeting Room"));

        // Test with invalid characters
        assertThrows(IllegalArgumentException.class, () -> parser.parse("Invalid$Tag"));
    }
}
