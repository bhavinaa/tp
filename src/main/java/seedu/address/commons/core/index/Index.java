package seedu.address.commons.core.index;

/**
 * Represents a zero-based index.
 */
public class Index {
    private final int zeroBasedIndex;

    private Index(int zeroBasedIndex) {
        this.zeroBasedIndex = zeroBasedIndex;
    }

    /**
     * Returns an Index using zero-based numbering.
     */
    public static Index fromZeroBased(int zeroBasedIndex) {
        return new Index(zeroBasedIndex);
    }

    /**
     * Returns an Index using one-based numbering.
     */
    public static Index fromOneBased(int oneBasedIndex) {
        return new Index(oneBasedIndex - 1);
    }

    public int getZeroBased() {
        return zeroBasedIndex;
    }

    public int getOneBased() {
        return zeroBasedIndex + 1;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Index && zeroBasedIndex == ((Index) other).zeroBasedIndex);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(zeroBasedIndex);
    }

    @Override
    public String toString() {
        return String.format("[%d]", zeroBasedIndex);
    }
}
