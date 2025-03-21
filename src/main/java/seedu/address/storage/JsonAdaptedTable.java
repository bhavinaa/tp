package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.RsvpList;
import seedu.address.model.table.Table;

/**
 * Jackson-friendly version of {@link Table}.
 */
public class JsonAdaptedTable {
    private final int tableId;
    private final int capacity;
    private final List<JsonAdaptedGuest> guests;

    /**
     * Constructs a {@code Table} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTable(@JsonProperty("tableId") int tableId,
                            @JsonProperty("capacity") int capacity,
                            @JsonProperty("guests") List<JsonAdaptedGuest> guests) {

        this.tableId = tableId;
        this.capacity = capacity;
        this.guests = (guests != null) ? guests : new ArrayList<>();
    }

    /**
     * Converts a given {@code Table} into this class for Jackson use.
     */
    public JsonAdaptedTable(Table source) {
        this.tableId = source.getTableId();
        this.capacity = source.getCapacity();
        this.guests = source.getGuests().stream()
            .map(JsonAdaptedGuest::new)
            .collect(Collectors.toList());
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Table} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted table.
     */
    public Table toModelType() throws IllegalValueException {
        RsvpList rsvpList = new RsvpList();
        for (JsonAdaptedGuest g : guests) {
            rsvpList.add(g.toModelType());
        }
        Table table = new Table(tableId, capacity, rsvpList);
        return table;
    }
}
