package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AssignCommand object
 */
public class AssignCommandParser implements Parser<AssignCommand> {
    @Override
    public AssignCommand parse(String userInput) throws ParseException {
        String[] args = userInput.trim().split("t/");
        String nameOrIndex = args[0];
        String tags = " t/" + String.join(" t/", Arrays.copyOfRange(args, 1, args.length));

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(tags, PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)
                || userInput.equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }

        Set<Tag> tagList = new HashSet<>();
        Set<Tag> eventTagList = new HashSet<>();

        for (String tagValue : argMultimap.getAllValues(PREFIX_TAG)) {
            if (tagValue.startsWith("E-")) {
                Tag eventTag = ParserUtil.parseEventTag(tagValue.substring(2));
                eventTagList.add(eventTag);
            } else {
                Tag normalTag = ParserUtil.parseTag(tagValue);
                tagList.add(normalTag);
            }
        }

        try {
            Object parsedObject = ParserUtil.parseNameIndex(nameOrIndex);
            if (parsedObject instanceof Index) {
                Index index = (Index) parsedObject;
                String dummyName = " ";
                return new AssignCommand(index, dummyName, tagList, eventTagList);
            } else if (parsedObject instanceof Name) {
                Name name = (Name) parsedObject;
                String nameString = name.toString();
                return new AssignCommand(null, nameString, tagList, eventTagList);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
            }
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE), pe);
        }
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
