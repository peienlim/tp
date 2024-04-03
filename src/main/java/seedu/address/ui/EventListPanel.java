package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.tag.EventTag;

/**
 * Panel containing the list of events.
 */
public class EventListPanel extends UiPart<Region> {
    private static final String FXML = "EventListPanel.fxml";
    private static final EventTag DEFAULT_EVENT_TAG = new EventTag("All", "All contacts in EventBook",
            LocalDateTime.parse("2024-04-05T14:00:00"), LocalDateTime.parse("2024-04-05T14:00:00"));
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<EventTag> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableSet<EventTag> eventSet) {
        super(FXML);
        eventListView.setCellFactory(listView -> new EventListViewCell());
        addDefaultEventCard();
        addExistingTagEventCard(eventSet);

        eventListView.getSelectionModel().select(DEFAULT_EVENT_TAG);
    }

    private void addDefaultEventCard() {
        eventListView.getItems().add(DEFAULT_EVENT_TAG);
    }

    private void addExistingTagEventCard(ObservableSet<EventTag> eventSet) {
        List<EventTag> eventList = new ArrayList<>(eventSet);
        eventListView.getItems().addAll(eventList);
    }


    //@@author {peienlim}-reused
    //Reused from https://github.com/AY2324S1-CS2103T-F08-3/tp
    //(src/main/java/seedu/address/ui/AnimalListPanel.java) Lines 45 to 51 with minor modifications
    public void selectEvent(EventTag eventTag) {
        eventListView.getSelectionModel().select(eventTag);
    }

    public void clearEventSelection() {
        eventListView.getSelectionModel().select(DEFAULT_EVENT_TAG);
    }
    //@@author

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Event} using a {@code EventCard}.
     */
    class EventListViewCell extends ListCell<EventTag> {
        @Override
        protected void updateItem(EventTag eventTag, boolean empty) {
            super.updateItem(eventTag, empty);

            if (empty || eventTag == null) {
                setGraphic(null);
                setText(null);
            } else {
                EventCard eventCard = new EventCard(eventTag);
                setGraphic(eventCard.getRoot());
            }
        }
    }

}
