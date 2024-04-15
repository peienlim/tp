package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EventBookStorage eventBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EventBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.eventBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

    @Override
    public Path getEventBookFilePath() {
        return eventBookStorage.getEventBookFilePath();
    }

    @Override
    public Optional<ReadOnlyEventBook> readEventBook() throws DataLoadingException {
        return readEventBook(eventBookStorage.getEventBookFilePath());
    }

    @Override
    public Optional<ReadOnlyEventBook> readEventBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eventBookStorage.readEventBook(filePath);
    }

    @Override
    public void saveEventBook(ReadOnlyEventBook eventBook) throws IOException {
        saveEventBook(eventBook, eventBookStorage.getEventBookFilePath());
    }

    @Override
    public void saveEventBook(ReadOnlyEventBook eventBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eventBookStorage.saveEventBook(eventBook, filePath);
    }

}
