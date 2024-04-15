package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class EventBook implements ReadOnlyEventBook {

    public static final EventTag DEFAULT_EVENT_TAG = new EventTag("All", "All contacts in EventBook",
            LocalDateTime.parse("2024-04-05T14:00:00"), LocalDateTime.parse("2024-04-05T14:00:00"));

    private final UniquePersonList persons;
    private final UniqueTagList<Tag> tagList;
    private final UniqueTagList<EventTag> eventTagList;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        tagList = new UniqueTagList<Tag>();
        eventTagList = new UniqueTagList<EventTag>();
    }

    public EventBook() {}

    /**
     * Creates an EventBook using the Persons in the {@code toBeCopied}
     */
    public EventBook(ReadOnlyEventBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    public void setTagList(Set<Tag> tagList) {
        this.tagList.setTags(tagList);
    }

    public void setEventTagList(Set<EventTag> eventTagList) {
        this.eventTagList.setTags(eventTagList);
    }

    /**
     * Resets the existing data of this {@code EventBook} with {@code newData}.
     */
    public void resetData(ReadOnlyEventBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setTagList(newData.getTagList());
        setEventTagList(newData.getEventTagList());
    }

    //// person-level and tag-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a tag {@code tag} exists in the address book.
     */
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return tagList.contains(tag);
    }

    /**
     * Assigns the given tags {@code tags} to {@code targetPerson}.
     * {@code tags} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void assign(Person targetPerson, Set<Tag> tags, Set<Tag> eventTags) {
        targetPerson.addTags(tags);
        Set<EventTag> eventsToAdd = new HashSet<>();
        for (Tag t : eventTags) {
            EventTag eventTag = getEventTag(t.tagName);
            eventsToAdd.add(eventTag);
        }
        targetPerson.addEventTags(eventsToAdd);
    }

    /**
     * Returns true if an event tag {@code tag} exists in the address book.
     */
    public boolean hasEventTag(EventTag eventTag) {
        requireNonNull(eventTag);
        return eventTagList.contains(eventTag);
    }

    /**
     * Returns true if an event tag with name {@code eventTagName} exists in the address book.
     */
    public boolean hasEventTag(String eventTagName) {
        requireNonNull(eventTagName);
        return eventTagList.contains(eventTagName);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
        for (Tag tag:p.getTags()) {
            if (!hasTag(tag)) {
                addTag(tag);
            }
        }
        for (EventTag eventTag:p.getEventTags()) {
            if (!hasEventTag(eventTag)) {
                addEventTag(eventTag);
            }
        }
    }

    /**
     * Adds a tag to the address book.
     * The tag must not already exist in the address book.
     */
    public void addTag(Tag t) {
        tagList.add(t);
    }

    /**
     * Adds an event tag to the address book.
     * The event tag must not already exist in the address book.
     */
    public void addEventTag(EventTag t) {
        eventTagList.add(t);
    }

    /**
     * Gets an event tag to the address book.
     * The event tag must not already exist in the address book.
     */
    public EventTag getEventTag(String t) {
        return eventTagList.getEventTag(t);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    /**
     * Replaces the given tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The {@code editedTag} must not be the same as another tag in the address book.
     */
    public void setTags(Tag target, Tag editedTag) {
        requireNonNull(editedTag);

        tagList.replace(target, editedTag);
    }

    /**
     * Replaces the given event tag {@code target} in the list with {@code editedTag}.
     * {@code target} must exist in the address book.
     * The {@code editedTag} must not be the same as another event tag in the address book.
     */
    public void setEventTags(EventTag target, EventTag editedTag) {
        requireNonNull(editedTag);

        eventTagList.replace(target, editedTag);
    }

    /**
     * Removes {@code key} from this {@code EventBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Removes {@code key} from this {@code EventBook}.
     * {@code key} must exist in the address book.
     */
    public void removeTag(Tag key) {
        for (Person p:persons) {
            p.removeTag(key);
        }
        tagList.remove(key);
    }

    /**
     * Removes {@code key} event tag from this {@code EventBook}.
     * {@code key} must exist in the address book.
     */
    public void removeEventTag(EventTag key) {
        for (Person p:persons) {
            p.removeEventTag(key);
        }
        eventTagList.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("tags", tagList)
                .add("event tags", eventTagList)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableSet<Tag> getTagList() {
        return tagList.asUnmodifiableObservableSet();
    }

    @Override
    public ObservableSet<EventTag> getEventTagList() {
        return eventTagList.asUnmodifiableObservableSet();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventBook)) {
            return false;
        }

        EventBook otherEventBook = (EventBook) other;
        return persons.equals(otherEventBook.persons)
                && tagList.equals(otherEventBook.tagList)
                && eventTagList.equals(otherEventBook.eventTagList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons, tagList, eventTagList);
    }
}
