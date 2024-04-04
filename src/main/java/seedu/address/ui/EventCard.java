package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import seedu.address.model.tag.EventTag;

/**
 * An UI component that displays information of a {@code EventTag}.
 */
public class EventCard extends UiPart<Region> {

    private static final String FXML = "EventCard.fxml";

    public final EventTag eventTag;

    @FXML
    private HBox cardPane;
    @FXML
    private Label eventName;

    /**
     * Creates a {@code EventCard} with the given {@code EventTag} to display.
     */
    public EventCard(EventTag eventTag) {
        super(FXML);
        this.eventTag = eventTag;
        eventName.setText(eventTag.tagName);
        eventName.setFont(Font.font( 10));
    }
}
