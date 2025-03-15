package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;

/**
 * Contains integration tests (interaction with the Model) for {@code RemarkCommand}.
 */
public class RemarkCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        // ✅ Initialize the model with sample persons
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_addRemark_success() {
        Index index = INDEX_FIRST_PERSON; // Ensure this is valid
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        Remark remark = new Remark("Loves programming");
        Person editedPerson = personToEdit.withRemark(remark);

        RemarkCommand remarkCommand = new RemarkCommand(index, remark);

        String expectedMessage = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_removeRemark_success() {
        Index index = INDEX_FIRST_PERSON;
        Person personToEdit = model.getFilteredPersonList().get(index.getZeroBased());
        Person editedPerson = personToEdit.withRemark(new Remark("")); // Empty remark removes existing remark

        RemarkCommand remarkCommand = new RemarkCommand(index, new Remark(""));

        String expectedMessage = String.format(RemarkCommand.MESSAGE_DELETE_REMARK_SUCCESS, editedPerson.getName());

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(personToEdit, editedPerson);

        assertCommandSuccess(remarkCommand, model, expectedMessage, expectedModel);
    }
}
