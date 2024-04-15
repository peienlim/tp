package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalEventBook;
import static seedu.address.testutil.TypicalTags.FRIEND;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;
import seedu.address.testutil.PersonBuilder;

public class EventBookTest {

    private final EventBook eventBook = new EventBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), eventBook.getPersonList());
        assertEquals(Collections.emptySet(), eventBook.getTagList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyEventBook_replacesData() {
        EventBook newData = getTypicalEventBook();
        eventBook.resetData(newData);
        assertEquals(newData, eventBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        List<Tag> newTags = List.of();
        List<EventTag> newEventTags = List.of();
        EventBookStub newData = new EventBookStub(newPersons, newTags, newEventTags);

        assertThrows(DuplicatePersonException.class, () -> eventBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasPerson(null));
    }

    @Test
    public void hasTag_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> eventBook.hasTag((Tag) null));
    }

    @Test
    public void hasPerson_personNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasPerson(ALICE));
    }

    @Test
    public void hasTag_tagNotInEventBook_returnsFalse() {
        assertFalse(eventBook.hasTag(FRIEND));
    }

    @Test
    public void hasPerson_personInEventBook_returnsTrue() {
        eventBook.addPerson(ALICE);
        assertTrue(eventBook.hasPerson(ALICE));
    }

    @Test
    public void hasTag_tagInEventBook_returnsTrue() {
        eventBook.addTag(FRIEND);
        assertTrue(eventBook.hasTag(FRIEND));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInEventBook_returnsTrue() {
        eventBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(eventBook.hasPerson(editedAlice));
    }

    @Test
    public void hasTag_tagWithSameNameInEventBook_returnsTrue() {
        eventBook.addTag(FRIEND);
        Tag editedFriend = new Tag("friends");
        assertTrue(eventBook.hasTag(editedFriend));
    }

    @Test
    public void addDuplicatePerson_throwsDuplicatePersonException() {
        eventBook.addPerson(ALICE);
        assertThrows(DuplicatePersonException.class, () -> eventBook.addPerson(ALICE));
    }

    @Test
    public void addDuplicateTag_throwsDuplicateTagException() {
        eventBook.addTag(FRIEND);
        assertThrows(DuplicateTagException.class, () -> eventBook.addTag(FRIEND));
    }

    @Test
    public void setPerson_withDifferentIdentityFields_success() {
        eventBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertDoesNotThrow(() -> eventBook.setPerson(ALICE, editedAlice));
    }

    @Test
    public void setTags_withUniqueTags_success() {
        Set<Tag> uniqueTags = new HashSet<>(Arrays.asList(FRIEND, new Tag("colleague")));
        assertDoesNotThrow(() -> eventBook.setTagList(uniqueTags));
    }

    @Test
    public void removePerson_notExistingPerson_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> eventBook.removePerson(ALICE));
    }

    @Test
    public void removeTag_notExistingTag_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> eventBook.removeTag(FRIEND));
    }



    @Test
    public void removePerson_existingPerson_success() {
        eventBook.addPerson(ALICE);
        assertDoesNotThrow(() -> eventBook.removePerson(ALICE));
    }

    @Test
    public void removeTag_existingTag_success() {
        eventBook.addTag(FRIEND);
        assertDoesNotThrow(() -> eventBook.removeTag(FRIEND));
    }


    @Test
    public void toStringMethod() {
        String expected = EventBook.class.getCanonicalName() + "{persons=" + eventBook.getPersonList()
                + ", tags=" + eventBook.getTagList() + ", event tags=" + eventBook.getEventTagList() + "}";
        assertEquals(expected, eventBook.toString());
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventBook.getPersonList().remove(0));
    }

    @Test
    public void getTagList_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> eventBook.getTagList().remove(FRIEND));
    }

    /**
     * A stub ReadOnlyEventBook whose persons list can violate interface constraints.
     */
    private static class EventBookStub implements ReadOnlyEventBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableSet<Tag> tags = FXCollections.observableSet();
        private final ObservableSet<EventTag> eventTags = FXCollections.observableSet();

        EventBookStub(Collection<Person> persons, Collection<Tag> tags, Collection<EventTag> eventTags) {
            this.persons.setAll(persons);
            this.tags.addAll(tags);
            this.eventTags.addAll(eventTags);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableSet<Tag> getTagList() {
            return tags;
        }

        @Override
        public ObservableSet<EventTag> getEventTagList() {
            return eventTags;
        }
    }

}
