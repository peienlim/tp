package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an EventTag in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTagName(String)}
 */
public class EventTag extends Tag {
    public final String description;

    public final LocalDateTime startDate;

    public final LocalDateTime endDate;
    /**
     * Constructs a {@code EventTag}.
     *
     * @param tagName A valid tag name.
     * @param description A Description for the event.
     */
    public EventTag(String tagName, String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(tagName);
        requireNonNull(tagName);
        checkArgument(isValidTagName(tagName), MESSAGE_CONSTRAINTS);
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCodeFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "t/E-" + tagName + " dc/" + description + " sd/" + startDate.format(formatter)
                + " ed/" + endDate.format(formatter);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventTag)) {
            return false;
        }

        EventTag otherTag = (EventTag) other;
        boolean nameEqual = tagName.equals(otherTag.tagName) && description.equals(otherTag.description);
        boolean timeEqual = startDate.isEqual(((EventTag) other).startDate)
                && endDate.equals(((EventTag) other).endDate);
        return nameEqual && timeEqual;
    }

    /**
     * Format state as text for viewing.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String startTime = startDate.format(formatter);
        String endTime = endDate.format(formatter);
        return '[' + tagName + ']' + " from " + startTime + " to " + endTime + ".\n" + description;
    }
}
