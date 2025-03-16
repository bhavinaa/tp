// package seedu.address.logic.parser;
//
// import static java.util.Objects.requireNonNull;
// import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
//
// import seedu.address.commons.core.index.Index;
// import seedu.address.logic.commands.RemarkCommand;
// import seedu.address.logic.parser.exceptions.ParseException;
// import seedu.address.model.person.Remark;
//
// /**
//  * Parses input arguments and creates a new RemarkCommand object
//  */
// public class RemarkCommandParser implements Parser<RemarkCommand> {
//
//     /**
//      * Parses the given {@code String} of arguments in the context of the RemarkCommand
//      * and returns a RemarkCommand object for execution.
//      * @throws ParseException if the user input does not conform to the expected format
//      */
//     public RemarkCommand parse(String args) throws ParseException {
//         requireNonNull(args);
//         ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);
//
//         Index index;
//         try {
//             index = ParserUtil.parseIndex(argMultimap.getPreamble());
//         } catch (ParseException pe) {
//             throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), pe);
//         }
//
//         Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
//
//         return new RemarkCommand(index, remark);
//     }
//
// }
package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Remark;



/**
 * Parses input arguments and creates a new RemarkCommand object.
 */
public class RemarkCommandParser implements Parser<RemarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemarkCommand
     * and returns a RemarkCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);

        // Tokenize the arguments (assuming remark has the prefix r/)
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_REMARK);

        // Ensure there's a valid index
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), pe);
        }

        // Extract remark (default to empty string if no remark is given)
        String remarkText = argMultimap.getValue(CliSyntax.PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, new Remark(remarkText));
    }
}

