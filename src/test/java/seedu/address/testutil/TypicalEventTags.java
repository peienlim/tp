package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.tag.EventTag;

/**
 * A utility class containing a list of {@code Tag} objects to be used in tests.
 */
public class TypicalEventTags {
    public static final EventTag MEETING = new EventTag("meeting",
            "Team meeting",
            LocalDateTime.parse("2024-04-01T09:00:00"),
            LocalDateTime.parse("2024-04-01T10:00:00"));
    public static final EventTag PRESENTATION = new EventTag("presentation",
            "Project presentation",
            LocalDateTime.parse("2024-04-05T14:00:00"),
            LocalDateTime.parse("2024-04-05T16:00:00"));
    public static final EventTag BIRTHDAY = new EventTag("birthday",
            "Birthday party",
            LocalDateTime.parse("2024-04-10T18:00:00"),
            LocalDateTime.parse("2024-04-10T22:00:00"));

    private TypicalEventTags() {}

    public static List<EventTag> getTypicalEventTags() {
        return new ArrayList<>(Arrays.asList(MEETING, PRESENTATION, BIRTHDAY));
    }
}
