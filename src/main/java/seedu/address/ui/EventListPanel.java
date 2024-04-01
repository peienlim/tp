package seedu.address.ui;

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
    private final Logger logger = LogsCenter.getLogger(EventListPanel.class);

    @FXML
    private ListView<EventTag> eventListView;

    /**
     * Creates a {@code EventListPanel} with the given {@code ObservableList}.
     */
    public EventListPanel(ObservableSet<EventTag> eventSet) {
        super(FXML);
        List<EventTag> eventList = new ArrayList<>(eventSet);
        eventListView.getItems().addAll(eventList);
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
