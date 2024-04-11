package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import seedu.address.model.person.exceptions.PersonNotFoundException;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * Represents a list of unique tags.
 * A UniqueTagList ensures that no duplicate tags are stored.
 */
public class UniqueTagList<T extends Tag> implements Iterable<T> {

    private final ObservableSet<T> internalSet = FXCollections.observableSet();

    /**
     * Constructs an empty UniqueTagList.
     */
    public UniqueTagList() {}

    /**
     * Constructs a UniqueTagList with the given tags.
     * @param tags A list of tags.
     */
    public UniqueTagList(Set<T> tags) {
        internalSet.addAll(tags);
    }

    /**
     * Returns true if the list contains an equivalent tag as the given argument of type Tag.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalSet.stream().anyMatch(tag -> tag.equals(toCheck));
    }

    /**
     * Returns true if the list contains an equivalent tag as the given argument of type String.
     */
    public boolean contains(String toCheck) {
        requireNonNull(toCheck);
        return internalSet.stream().anyMatch(tag -> tag.tagName.equals(toCheck));
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalSet.add(toAdd);
    }

    /**
     * Replaces the specified tag in the list with the new tag.
     * @param oldTag The tag to be replaced.
     * @param newTag The new tag to replace the old tag.
     */
    public void replace(T oldTag, T newTag) {
        requireAllNonNull(oldTag, newTag);

        if (!internalSet.contains(oldTag)) {
            throw new TagNotFoundException();
        }

        if (!oldTag.isSameTag(newTag) && contains(newTag)) {
            throw new DuplicateTagException();
        }

        internalSet.remove(oldTag);
        internalSet.add(newTag);
    }

    /**
     * Removes the equivalent tag from the list.
     * The argument of type Tag must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalSet.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    /**
     * Removes the equivalent tag from the list.
     * The argument of type String must exist in the list.
     */
    public void remove(String toRemove) {
        requireNonNull(toRemove);
        if (!internalSet.removeIf(tag -> tag.tagName.equals(toRemove))) {
            throw new TagNotFoundException();
        }
    }
    /**
     * Replaces all tags in this list with the tags from the replacement list.
     * @param replacement The replacement UniqueTagList.
     */
    public void setTags(UniqueTagList<T> replacement) {
        requireNonNull(replacement);
        internalSet.clear();
        internalSet.addAll(replacement.internalSet);
    }

    /**
     * Replaces the tags in the list with the given set of tags.
     * @param tags The set of tags to replace the current tags.
     */
    public void setTags(Set<T> tags) {
        requireAllNonNull(tags);
        internalSet.clear();
        internalSet.addAll(tags);
    }

    /**
     * Returns the event tag with the specified event tag name, if it exists in the list.
     * Returns null if no such event tag is found.
     * @param eventTagName The name of the event tag to retrieve.
     * @return The event tag with the specified event tag name, or null if not found.
     */
    public EventTag getEventTag(String eventTagName) {
        requireNonNull(eventTagName);
        for (T tag : internalSet) {
            if (tag instanceof EventTag && tag.tagName.equals(eventTagName)) {
                return (EventTag) tag;
            }
        }
        return null;
    }

    /**
     * Removes all the event tags given a specified event tag name, if it exists in the list.
     * Returns null if no such event tag is found.
     * @param eventTagName The name of the event tag to retrieve.
     * @return The event tag with the specified event tag name, or null if not found.
     */
    public EventTag removeEvent(String eventTagName) {
        requireNonNull(eventTagName);
        for (T tag : internalSet) {
            if (tag instanceof EventTag && tag.tagName.equals(eventTagName)) {
                internalSet.removeIf(t -> t.equals(tag));
                return (EventTag) tag;
            }
        }
        return null;
    }

    /**
     * Returns the backing set as an unmodifiable {@code ObservableSet}.
     */
    public ObservableSet<T> asUnmodifiableObservableSet() {
        return FXCollections.unmodifiableObservableSet(internalSet);
    }

    @Override
    public Iterator<T> iterator() {
        return internalSet.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // handle nulls
        if (!(other instanceof UniqueTagList<?>)) {
            return false;
        }

        UniqueTagList<?> otherUniqueTagSet = (UniqueTagList<?>) other;
        return internalSet.equals(otherUniqueTagSet.internalSet);
    }

    @Override
    public String toString() {
        return internalSet.toString();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(Set<T> tags) {
        Set<T> uniqueTags = new HashSet<>();
        for (T tag : tags) {
            if (!uniqueTags.add(tag)) {
                return false; // Duplicate tag found
            }
        }
        return true; // No duplicate tags found
    }

}
