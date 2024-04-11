package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {

    private final UniqueTagList<Tag> uniqueTagList = new UniqueTagList<>();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains((Tag) null));
    }

    @Test
    public void contains_nullTagString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains((String) null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void contains_tagNotInListString_returnsFalse() {
        assertFalse(uniqueTagList.contains("friends"));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(new Tag("friends"));
        assertTrue(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void contains_tagInListString_returnsTrue() {
        uniqueTagList.add(new Tag("friends"));
        assertTrue(uniqueTagList.contains("friends"));
    }

    @Test
    public void contains_tagWithSameNameInList_returnsTrue() {
        uniqueTagList.add(new Tag("friends"));
        assertTrue(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(new Tag("friends"));
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(new Tag("friends")));
    }

    @Test
    public void replace_validTags_replacesTag() {
        Tag oldTag = new Tag("old");
        Tag newTag = new Tag("new");
        uniqueTagList.add(oldTag);
        uniqueTagList.replace(oldTag, newTag);
        assertTrue(uniqueTagList.contains(newTag) && !uniqueTagList.contains(oldTag));
    }

    @Test
    public void replace_nonExistingOldTag_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.replace(new Tag("old"), new Tag("new")));
    }

    @Test
    public void replace_existingNewTag_throwsDuplicateTagException() {
        uniqueTagList.add(new Tag("old"));
        uniqueTagList.add(new Tag("new"));
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.replace(new Tag("old"), new Tag("new")));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove((Tag) null));
    }

    @Test
    public void remove_nullTagString_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove((String) null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(new Tag("friends")));
    }

    @Test
    public void remove_tagDoesNotExistString_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove("friends"));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(new Tag("friends"));
        uniqueTagList.remove(new Tag("friends"));
        UniqueTagList<Tag> expectedUniqueTagList = new UniqueTagList<>();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void remove_existingTagString_removesTag() {
        uniqueTagList.add(new Tag("friends"));
        uniqueTagList.remove("friends");
        UniqueTagList<Tag> expectedUniqueTagList = new UniqueTagList<>();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }
    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList<Tag>) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(new Tag("friends"));
        UniqueTagList<Tag> expectedUniqueTagList = new UniqueTagList<>();
        expectedUniqueTagList.add(new Tag("colleagues"));
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((Set<Tag>) null));
    }

    @Test
    public void setTags_set_replacesOwnListWithProvidedSet() {
        uniqueTagList.add(new Tag("friends"));
        Set<Tag> tagSet = new HashSet<>(Arrays.asList(new Tag("colleagues"), new Tag("family")));
        uniqueTagList.setTags(tagSet);
        UniqueTagList<Tag> expectedUniqueTagList = new UniqueTagList<>();
        expectedUniqueTagList.add(new Tag("colleagues"));
        expectedUniqueTagList.add(new Tag("family"));
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void getEventTag_existingEventTag_returnsEventTag() {
        EventTag eventTag = new EventTag("event", "get event tag test",
                LocalDateTime.now(), LocalDateTime.now());
        uniqueTagList.add(eventTag);
        assertSame(eventTag, uniqueTagList.getEventTag("event"));
    }

    @Test
    public void getEventTag_nonExistingEventTag_returnsNull() {
        assertNull(uniqueTagList.getEventTag("nonExistingEvent"));
    }

    @Test
    public void removeEvent_existingEventTag_removesEventTag() {
        EventTag eventTag = new EventTag("event", "get event tag test",
                LocalDateTime.now(), LocalDateTime.now());
        uniqueTagList.add(eventTag);
        assertNotNull(uniqueTagList.removeEvent("event"));
        assertFalse(uniqueTagList.contains(eventTag));
    }

    @Test
    public void removeEvent_nonExistingEventTag_returnsNull() {
        assertNull(uniqueTagList.removeEvent("nonExistingEvent"));
    }

    @Test
    public void asUnmodifiableObservableSet_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTagList.asUnmodifiableObservableSet().remove(new Tag("friends")));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTagList.asUnmodifiableObservableSet().toString(), uniqueTagList.toString());
    }
}

