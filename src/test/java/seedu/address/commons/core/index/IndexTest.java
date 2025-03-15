package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void fromZeroBased_validIndex_success() {
        Index index = Index.fromZeroBased(2);
        assertEquals(2, index.getZeroBased());
        assertEquals(3, index.getOneBased());
    }

    @Test
    public void fromOneBased_validIndex_success() {
        Index index = Index.fromOneBased(3);
        assertEquals(2, index.getZeroBased());
        assertEquals(3, index.getOneBased());
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        Index index1 = Index.fromZeroBased(1);
        Index index2 = Index.fromZeroBased(1);
        assertEquals(index1, index2);
    }
}
