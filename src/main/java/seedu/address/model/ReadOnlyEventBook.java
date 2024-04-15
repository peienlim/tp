package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.model.person.Person;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyEventBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    ObservableSet<Tag> getTagList();

    ObservableSet<EventTag> getEventTagList();
}
