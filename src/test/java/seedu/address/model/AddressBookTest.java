package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.exceptions.NoCurrentWeddingException;
import seedu.address.model.person.Person;
import seedu.address.model.table.Table;
import seedu.address.model.wedding.UniqueWeddingList;
import seedu.address.model.wedding.Wedding;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();
    @Test
    public void constructor() {
        addressBook.addWedding(new Wedding("Test"));
        addressBook.setCurrentWeddingByName("Test");
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
        assertTrue(addressBook.hasCurrentWedding());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    //    To rewrite
    //    @Test
    //    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
    //        // Two persons with the same identity fields
    //        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
    //                .build();
    //        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
    //        AddressBookStub newData = new AddressBookStub(newPersons);
    //
    //        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    //    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        addressBook.addWedding(new Wedding("Test"));
        addressBook.setCurrentWeddingByName("Test");
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addWedding(new Wedding("Test"));
        addressBook.setCurrentWeddingByName("Test");
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addWedding(new Wedding("Test"));
        addressBook.setCurrentWeddingByName("Test");
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void noCurrentWedding_addPerson_throwsNoCurrentWeddingException() {
        AddressBookStubWithoutWedding addressBookStub = new AddressBookStubWithoutWedding();
        assertThrows(NoCurrentWeddingException.class, () -> addressBookStub.addPerson(ALICE));
    }

    //    To rewrite

    //    @Test
    //    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
    //        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    //    }

    //    @Test
    //    public void toStringMethod() {
    //        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
    //        assertEquals(expected, addressBook.toString());
    //    }

    /**
     * A stub AddressBook without a current wedding set.
     */
    private static class AddressBookStubWithoutWedding extends AddressBook {
        @Override
        public Wedding getCurrentWedding() {
            return null;
        }

        @Override
        public void addPerson(Person person) {
            throw new NoCurrentWeddingException();
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return FXCollections.observableArrayList();
        }

        @Override
        public ObservableList<Table> getTableList() {
            return FXCollections.observableArrayList();
        }
    }
    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {

        private final UniqueWeddingList weddingList = new UniqueWeddingList();

        private final Wedding wedding = new Wedding("test-wedding");

        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Table> tables = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
            weddingList.addWedding(wedding);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Table> getTableList() {
            return tables;
        }

        @Override
        public Wedding getCurrentWedding() {
            if (wedding == null) {
                throw new NoSuchElementException("No wedding found in AddressBookStub.");
            }
            return wedding;
        }

        @Override
        public boolean hasCurrentWedding() {
            return true;
        }


        @Override
        public ObservableList<Wedding> getWeddingList() {
            return weddingList.asUnmodifiableObservableList();
        }
    }

}
