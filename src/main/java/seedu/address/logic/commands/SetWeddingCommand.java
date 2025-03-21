package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.wedding.Wedding;

/**
 * Sets a new wedding in the model.
 */
public class SetWeddingCommand extends Command {

    public static final String COMMAND_WORD = "setWedding";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets the current wedding.\n"
            + "Parameters: WEDDING_NAME\n"
            + "Example: " + COMMAND_WORD + " John and Jane's Wedding";

    public static final String MESSAGE_SUCCESS = "Current wedding set to: %1$s";
    public static final String MESSAGE_WEDDING_ALREADY_EXISTS =
            "A different wedding exists. Delete the current wedding first.";
    public static final String MESSAGE_WEDDING_ALREADY_SET =
            "The wedding is already set to: %1$s";

    private final String weddingName;

    public SetWeddingCommand(String weddingName) {
        this.weddingName = weddingName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Wedding existingWedding = model.getCurrentWedding();

        //  Allow setting a new wedding if none exists
        if (existingWedding == null) {
            Wedding newWedding = new Wedding(weddingName);
            model.setCurrentWedding(newWedding);
            return new CommandResult(String.format(MESSAGE_SUCCESS, weddingName));
        }

        //  If the same wedding is already set, return a message
        if (existingWedding.getName().equals(weddingName)) {
            return new CommandResult(String.format(MESSAGE_WEDDING_ALREADY_SET, weddingName));
        }

        // If a **different** wedding exists, prevent setting a new one
        throw new CommandException(MESSAGE_WEDDING_ALREADY_EXISTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SetWeddingCommand
                && weddingName.equals(((SetWeddingCommand) other).weddingName));
    }
}
