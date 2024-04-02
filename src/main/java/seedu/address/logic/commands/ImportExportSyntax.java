package seedu.address.logic.commands;

import java.util.List;
import java.util.Map;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class ImportExportSyntax {
    public static final List<String> FIELDS = List.of(
            "NAME", "NUMBER", "EMAIL", "ADDRESS", "TAG"
    ); // TO hold the fields present in the csv
    public static final int NUMBER_OF_FIELDS = FIELDS.size();

    public static final Map<String, String> PREFIX_MAP = Map.of(
            "NAME", "n/",
            "NUMBER", "p/",
            "EMAIL", "e/",
            "ADDRESS", "a/",
            "TAG", "t/"
    ); // To format the data in the csv to command format
}
