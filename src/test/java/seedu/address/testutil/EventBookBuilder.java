package seedu.address.testutil;

import seedu.address.model.EventBook;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class EventBookBuilder {

    private EventBook eventBook;

    public EventBookBuilder() {
        eventBook = new EventBook();
    }

    public EventBookBuilder(EventBook eventBook) {
        this.eventBook = eventBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public EventBookBuilder withPerson(Person person) {
        eventBook.addPerson(person);
        return this;
    }

    public EventBook build() {
        return eventBook;
    }
}
