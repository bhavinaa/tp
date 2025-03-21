package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Guest;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.category.DietaryRestriction;
import seedu.address.model.person.category.Rsvp;
import seedu.address.model.wedding.Wedding;

public class DeleteGuestCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        Wedding currentWedding = new Wedding("John and Jane's Wedding");
        model.addWedding(currentWedding);
        model.setCurrentWedding(currentWedding);
    }

    @Test
    public void execute_removeGuestByPhone_success() throws Exception {
        Guest guest = new Guest(
            new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.YES),
            null);
        model.getCurrentWedding().addGuest(guest);
        DeleteGuestCommand command = new DeleteGuestCommand(new Phone("12345678"), null);
        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteGuestCommand.MESSAGE_SUCCESS, guest), result.getFeedbackToUser());
    }

    @Test
    public void execute_removeGuestByPhone_failure() {
        DeleteGuestCommand command = new DeleteGuestCommand(new Phone("12345674"), null);
        assertThrows(CommandException.class, () -> command.execute(model));
    }

    @Test
    public void execute_removeGuestById_success() throws CommandException {
        Guest guest = new Guest(new Name("John Doe"),
            new Phone("12345678"),
            new Email("johndoe@example.com"),
            new Address("123 Street"),
            new DietaryRestriction(DietaryRestriction.TypicalRestriction.NONE),
            new Rsvp(Rsvp.Status.YES),
            null);
        model.getCurrentWedding().addGuest(guest);
        DeleteGuestCommand command = new DeleteGuestCommand(null, new Name("John Doe"));
        CommandResult result = command.execute(model);
        assertEquals(String.format(DeleteGuestCommand.MESSAGE_SUCCESS, guest), result.getFeedbackToUser());
    }

    @Test
    public void execute_removeGuestById_failure() {
        DeleteGuestCommand command = new DeleteGuestCommand(new Phone("12345674"), null);
        assertThrows(CommandException.class, () -> command.execute(model));
    }


    // @Test
    // public void execute_noCurrentWedding_throwsCommandException() {
    //     model.setCurrentWedding(null); // Set currentWedding to null
    //
    //     Guest guest = new Guest(new Name("John Doe"),
    //         new Phone("12345678"),
    //         new Email("johndoe@example.com"),
    //         new Address("123 Street"),
    //         new DietaryRestriction("None"),
    //         new Rsvp(Rsvp.Status.YES));
    //     RemoveGuestCommand command = new RemoveGuestCommand(new Phone("12345678"), 0);
    //
    //     assertThrows(CommandException.class, () -> command.execute(model), "No Current Wedding");
    // }
}
