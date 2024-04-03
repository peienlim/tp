package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<EventTag> eventTags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Set<EventTag> eventTags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.eventTags.addAll(eventTags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable event tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<EventTag> getEventTags() {
        return Collections.unmodifiableSet(eventTags);
    }

    /**
     * Returns true if a person contains a tag, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public boolean containsTag(Tag tag) {
        for (Tag t : tags) {
            if (t.isSameTag(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns true if a person contains a tag, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public boolean containsEventTag(EventTag tag) {
        for (EventTag t : eventTags) {
            if (t.isSameTag(tag)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a tag from the Person if it exists in the tags for this Person.
     */
    public void removeTag(Tag key) {
        if (this.containsTag(key)) {
            tags.remove(key);
        }
    }

    /**
     * Adds a tag to the Person.
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Removes a tag from the Person if it exists in the tags for this Person.
     */
    public void removeEventTag(EventTag key) {
        if (this.containsTag(key)) {
            eventTags.remove(key);
        }
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && eventTags.equals(otherPerson.eventTags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("eventTags", eventTags)
                .toString();
    }

}
