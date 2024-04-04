package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import seedu.address.logic.commands.CtagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.EventTag;
import seedu.address.model.tag.Tag;

import java.time.LocalDateTime;
import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new CtagCommand object
 */
public class CtagCommandParser implements Parser<CtagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CtagCommand
     * and returns a CtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CtagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String tagName = args.trim();
        CtagCommand cmd = new CtagCommand(null, null);
        if (tagName.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CtagCommand.MESSAGE_USAGE));
        }

        boolean isEventTag = ArgumentTokenizer.findPrefixPosition(args, PREFIX_EVENT_TAG.getPrefix(), 0) != -1;
        boolean isTag = tagName.split(" ").length == 1;
        //check type of tag: e-tag or normal tag
        if (isTag) {
            //if we have a tag string
            Tag tag = ParserUtil.parseTag(tagName);
            cmd = new CtagCommand(tag, null);
        } else if (isEventTag) {
            //if we have an event tag
            ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                    PREFIX_EVENT_TAG, PREFIX_DESCRIPTION, PREFIX_START_DATE, PREFIX_END_DATE);
            if (!containsAllPrefix(argMultimap, PREFIX_EVENT_TAG, PREFIX_DESCRIPTION, PREFIX_START_DATE, PREFIX_END_DATE)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, CtagCommand.MESSAGE_USAGE));
            }
            argMultimap.verifyNoDuplicatePrefixesFor(
                    PREFIX_EVENT_TAG, PREFIX_DESCRIPTION, PREFIX_START_DATE, PREFIX_END_DATE);
            String name =  argMultimap.getValue(PREFIX_EVENT_TAG).get();
            LocalDateTime sd = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_START_DATE).get());
            LocalDateTime ed = ParserUtil.parseDateTime(argMultimap.getValue(PREFIX_END_DATE).get());
            String description = argMultimap.getValue(PREFIX_DESCRIPTION).get();
            EventTag eventTag = new EventTag(name, description, sd, ed);
            cmd = new CtagCommand(null, eventTag);
        }
        return cmd;
    }

    /**
     * Check if all prefixes have input, returns true if so
     * and returns a CtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public boolean containsAllPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
