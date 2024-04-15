package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.EventBook;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableEventBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");
    private static final Path DUPLICATE_TAG_FILE = TEST_DATA_FOLDER.resolve("duplicateTagAddressBook.json");

    @Test
    public void toModelType_typicalPersonsAndTagsFile_success() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableEventBook.class).get();
        EventBook eventBookFromFile = dataFromFile.toModelType();
        EventBook typicalPersonsEventBook = TypicalPersons.getTypicalEventBook();
        assertEquals(eventBookFromFile, typicalPersonsEventBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventBook.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateTags_throwsIllegalValueException() throws Exception {
        JsonSerializableEventBook dataFromFile = JsonUtil.readJsonFile(DUPLICATE_TAG_FILE,
                JsonSerializableEventBook.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableEventBook.MESSAGE_DUPLICATE_TAG,
                dataFromFile::toModelType);
    }

}
