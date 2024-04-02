package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link EventTag}.
 */
class JsonAdaptedEventTag {

    private final String tagName;
    private final String description;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    /**
     * Constructs a {@code JsonAdaptedEventTag} with the given details.
     */
    @JsonCreator
    public JsonAdaptedEventTag(@JsonProperty("tagName") String tagName,
                               @JsonProperty("description") String description,
                               @JsonProperty("startDate") LocalDateTime startDate,
                               @JsonProperty("endDate") LocalDateTime endDate) {
        this.tagName = tagName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Converts a given {@code EventTag} into this class for Jackson use.
     */
    public JsonAdaptedEventTag(EventTag source) {
        tagName = source.tagName;
        description = source.description;
        startDate = source.startDate;
        endDate = source.endDate;
    }

    @JsonProperty("tagName")
    public String getTagName() {
        return tagName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    /**
     * Converts this Jackson-friendly adapted event tag object into the model's {@code EventTag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public EventTag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new EventTag(tagName, description, startDate, endDate);
    }
}
