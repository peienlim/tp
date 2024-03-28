package seedu.address.model.tag;

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
}
