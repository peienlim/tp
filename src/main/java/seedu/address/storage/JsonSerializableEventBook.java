package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.EventBook;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.person.Person;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * An Immutable EventBook that is serializable to JSON format.
 */
@JsonRootName(value = "eventbook")
class JsonSerializableEventBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_TAG = "Tags list contains duplicate tag(s).";
    public static final String MESSAGE_DUPLICATE_EVENT_TAG = "Events tag list contains duplicate event tag(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedTag> tagList = new ArrayList<>();
    private final List<JsonAdaptedEventTag> eventTagList = new ArrayList<>();


    /**
     * Constructs a {@code JsonSerializableEventBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableEventBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                     @JsonProperty("tagList") List<JsonAdaptedTag> tags,
                                     @JsonProperty("eventTagList") List<JsonAdaptedEventTag> eventTags) {
        this.persons.addAll(persons);
        this.tagList.addAll(tags);
        this.eventTagList.addAll(eventTags);
    }

    /**
     * Converts a given {@code ReadOnlyEventBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableEventBook}.
     */
    public JsonSerializableEventBook(ReadOnlyEventBook source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        tagList.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
        eventTagList.addAll(source.getEventTagList().stream()
                .map(JsonAdaptedEventTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code EventBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public EventBook toModelType() throws IllegalValueException {
        EventBook eventBook = new EventBook();
        for (JsonAdaptedTag jsonAdaptedTag : tagList) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (eventBook.hasTag(tag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TAG);
            }
            eventBook.addTag(tag);
        }
        for (JsonAdaptedEventTag jsonAdaptedEventTag : eventTagList) {
            EventTag eventTag = jsonAdaptedEventTag.toModelType();
            if (eventBook.hasEventTag(eventTag)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EVENT_TAG);
            }
            eventBook.addEventTag(eventTag);
        }
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (eventBook.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            eventBook.addPerson(person);
        }
        return eventBook;
    }

}
