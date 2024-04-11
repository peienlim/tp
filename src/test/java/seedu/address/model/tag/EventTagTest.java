package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class EventTagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventTag(null, "test",
                LocalDateTime.now(), LocalDateTime.now()));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new EventTag(invalidTagName, "test",
                LocalDateTime.now(), LocalDateTime.now()));
    }
    @Test
    public void getCodeFormat_validInput_returnsFormattedString() {
        LocalDateTime startDate = LocalDateTime.of(2022, 3, 15, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 3, 15, 12, 0);
        EventTag eventTag = new EventTag("Meeting", "Discuss project", startDate, endDate);

        String expectedCodeFormat = "t/E-Meeting dc/Discuss project sd/2022-03-15 10:00:00 ed/2022-03-15 12:00:00";
        assertEquals(expectedCodeFormat, eventTag.getCodeFormat());
    }

    @Test
    public void equals_equalObjects_returnsTrue() {
        LocalDateTime startDate1 = LocalDateTime.of(2022, 3, 15, 10, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2022, 3, 15, 12, 0);
        EventTag eventTag1 = new EventTag("Meeting", "Discuss project", startDate1, endDate1);

        LocalDateTime startDate2 = LocalDateTime.of(2022, 3, 15, 10, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2022, 3, 15, 12, 0);
        EventTag eventTag2 = new EventTag("Meeting", "Discuss project", startDate2, endDate2);

        assertEquals(eventTag1, eventTag2);
    }

    @Test
    public void equals_notEqualObjects_returnsFalse() {
        LocalDateTime startDate1 = LocalDateTime.of(2022, 3, 15, 10, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2022, 3, 15, 12, 0);
        EventTag eventTag1 = new EventTag("Meeting", "Discuss project", startDate1, endDate1);

        LocalDateTime startDate2 = LocalDateTime.of(2022, 3, 15, 11, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2022, 3, 15, 13, 0);
        EventTag eventTag2 = new EventTag("Meeting", "Discuss project", startDate2, endDate2);

        assertNotEquals(eventTag1, eventTag2);
    }

    @Test
    public void toString_validInput_returnsFormattedString() {
        LocalDateTime startDate = LocalDateTime.of(2022, 3, 15, 10, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 3, 15, 12, 0);
        EventTag eventTag = new EventTag("Meeting", "Discuss project", startDate, endDate);

        String expectedToString = "[Meeting] from 2022-03-15 10:00:00 to 2022-03-15 12:00:00.\nDiscuss project";
        assertEquals(expectedToString, eventTag.toString());
    }

}
