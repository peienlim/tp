package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link EventTag}.
 */
class JsonAdaptedEventTag extends JsonAdaptedTag {

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
        super(tagName);
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    /**
     * Converts a given {@code EventTag} into this class for Jackson use.
     */
    public JsonAdaptedEventTag(EventTag source) {
        super(source.tagName);
        description = source.description;
        startDate = source.startDate;
        endDate = source.endDate;
    }

    @JsonValue
    @Override
    public String getTagName() {
        return super.getTagName();
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
    @Override
    public EventTag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(super.getTagName())) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }

        return new EventTag(super.getTagName(), description, startDate, endDate);
    }
}
