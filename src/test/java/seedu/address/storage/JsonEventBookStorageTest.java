package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.HOON;
import static seedu.address.testutil.TypicalPersons.IDA;
import static seedu.address.testutil.TypicalPersons.getTypicalEventBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.EventBook;
import seedu.address.model.ReadOnlyEventBook;

public class JsonEventBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonAddressBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readEventBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readEventBook(null));
    }

    private java.util.Optional<ReadOnlyEventBook> readEventBook(String filePath) throws Exception {
        return new JsonEventBookStorage(Paths.get(filePath)).readEventBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readEventBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readEventBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readEventBook_invalidPersonEventBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEventBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readEventBook_invalidAndValidPersonEventBook_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readEventBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveEventBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        EventBook original = getTypicalEventBook();
        JsonEventBookStorage jsonEventBookStorage = new JsonEventBookStorage(filePath);

        // Save in new file and read back
        jsonEventBookStorage.saveEventBook(original, filePath);
        ReadOnlyEventBook readBack = jsonEventBookStorage.readEventBook(filePath).get();
        assertEquals(original, new EventBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonEventBookStorage.saveEventBook(original, filePath);
        readBack = jsonEventBookStorage.readEventBook(filePath).get();
        assertEquals(original, new EventBook(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonEventBookStorage.saveEventBook(original); // file path not specified
        readBack = jsonEventBookStorage.readEventBook().get(); // file path not specified
        assertEquals(original, new EventBook(readBack));

    }

    @Test
    public void saveEventBook_nullEventBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code eventBook} at the specified {@code filePath}.
     */
    private void saveEventBook(ReadOnlyEventBook eventBook, String filePath) {
        try {
            new JsonEventBookStorage(Paths.get(filePath))
                    .saveEventBook(eventBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveEventBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveEventBook(new EventBook(), null));
    }
}
