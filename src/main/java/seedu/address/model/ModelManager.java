package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Set;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final EventBook eventBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private EventTag currentEventTag;

    /**
     * Initializes a ModelManager with the given eventBook and userPrefs.
     */
    public ModelManager(ReadOnlyEventBook eventBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(eventBook, userPrefs);

        logger.fine("Initializing with event book: " + eventBook + " and user prefs " + userPrefs);

        this.eventBook = new EventBook(eventBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.eventBook.getPersonList());
        currentEventTag = null;
    }

    public ModelManager() {
        this(new EventBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getEventBookFilePath() {
        return userPrefs.getEventBookFilePath();
    }

    @Override
    public void setEventBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setEventBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setEventBook(ReadOnlyEventBook eventBook) {
        this.eventBook.resetData(eventBook);
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return eventBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return eventBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        eventBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        eventBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        eventBook.setPerson(target, editedPerson);
    }

    @Override
    public boolean hasTag(Tag tag) {
        requireNonNull(tag);
        return eventBook.hasTag(tag);
    }

    @Override
    public void assign(Person targetPerson, Set<Tag> tags, Set<Tag> eventTags) {
        requireAllNonNull(targetPerson, tags, eventTags);
        eventBook.assign(targetPerson, tags, eventTags);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void deleteTag(Tag tag) {
        eventBook.removeTag(tag);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void addTag(Tag tag) {
        eventBook.addTag(tag);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public boolean hasEventTag(EventTag tag) {
        requireNonNull(tag);
        return eventBook.hasEventTag(tag);
    }

    @Override
    public boolean hasEventTag(String tagName) {
        requireNonNull(tagName);
        return eventBook.hasEventTag(tagName);
    }

    @Override
    public void deleteEventTag(EventTag tag) {
        eventBook.removeEventTag(tag);
    }

    @Override
    public void addEventTag(EventTag tag) {
        eventBook.addEventTag(tag);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public EventTag getEventTag(String tag) {
        return eventBook.getEventTag(tag);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    @Override
    public void updateTagPersonList(Tag t) {
        requireNonNull(t);
        if (currentEventTag != null) {
            Predicate<Person> eventTagPredicate = person -> person.containsEventTag(currentEventTag);
            Predicate<Person> normalTagPredicate = person -> person.containsTag(t);
            filteredPersons.setPredicate(eventTagPredicate.and(normalTagPredicate));
        } else {
            filteredPersons.setPredicate(person -> person.containsTag(t));
        }
    }

    @Override
    public void updateEventTagPersonList(EventTag t) {
        requireNonNull(t);
        setCurrentEventTag(t);
        filteredPersons.setPredicate(person -> person.containsEventTag(currentEventTag));
    }

    /**
     * Sets the current event tag to filter by.
     * @param eventTag The event tag to filter by.
     */
    public void setCurrentEventTag(EventTag eventTag) {
        this.currentEventTag = eventTag;
    }

    /**
     * Clears the current event tag.
     */
    public void clearCurrentEventTag() {
        this.currentEventTag = null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return eventBook.equals(otherModelManager.eventBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPersons.equals(otherModelManager.filteredPersons);
    }

    //=========== Filtered Event List Accessors =============================================================
    /**
     * Returns an unmodifiable view of the list of {@code EventTag} backed by the internal list of
     * {@code versionedAddressBook}
     */
    public ObservableSet<EventTag> getEventTagList() {
        return eventBook.getEventTagList();
    }

}
