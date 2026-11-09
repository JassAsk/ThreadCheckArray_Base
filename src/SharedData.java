import java.util.ArrayList;

/**
 * Stores the input values and the state shared by the worker threads.
 */
public class SharedData {
    /** The input values used by the subset-sum search. */
    private ArrayList<Integer> array;
    /** Indicates which input positions participate in the discovered solution. */
    private boolean[] winArray;
    /** Indicates whether a solution has been found. */
    private boolean flag;
    /** The target value that the workers must find. */
    private final int b;

    /**
     * Creates shared data for an input list and target value.
     *
     * @param array input values
     * @param b target value to find
     */
    public SharedData(ArrayList<Integer> array, int b) {
        this.array = array;
        this.b = b;
    }

    /**
     * Returns the solution-position flags.
     *
     * @return the boolean array identifying selected input positions
     */
    public boolean[] getWinArray() { return winArray; }

    /**
     * Sets the solution-position flags.
     *
     * @param winArray boolean array identifying selected input positions
     */
    public void setWinArray(boolean[] winArray) { this.winArray = winArray; }

    /**
     * Returns the input values.
     *
     * @return the input values as an {@code ArrayList<Integer>}
     */
    public ArrayList<Integer> getArray() { return array; }

    /**
     * Returns the target value.
     *
     * @return the target value
     */
    public int getB() { return b; }

    /**
     * Returns whether a solution has been found.
     *
     * @return {@code true} when a solution exists; otherwise {@code false}
     */
    public boolean getFlag() { return flag; }

    /**
     * Updates the solution-found state.
     *
     * @param flag {@code true} when a solution has been found
     */
    public void setFlag(boolean flag) { this.flag = flag; }
}
