package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
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
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<EventTag> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableList<EventTag> eventList) {
        super(FXML);
        eventListView.setItems(eventList);
        eventListView.setCellFactory(listView -> new EventListViewCell());
    }

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
                setGraphic(new EventCard(eventTag).getRoot());
            }
        }
    }

}
