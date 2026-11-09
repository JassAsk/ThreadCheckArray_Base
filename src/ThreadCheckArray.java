import java.util.ArrayList;

/**
 * Searches input values for a subset whose sum equals the shared target.
 */
public class ThreadCheckArray implements Runnable {
    /** Indicates whether this worker has found a solution. */
    private boolean flag;
    /** Marks the input positions used by the found solution. */
    private boolean[] winArray;
    /** Shared state used to coordinate the worker threads. */
    SharedData sd;
    /** Input values searched by this worker. */
    ArrayList<Integer> array;
    /** Target value searched for by this worker. */
    int b;

    /**
     * Creates a worker for the supplied shared search state.
     *
     * @param sd shared input values, target, and result state
     */
    public ThreadCheckArray(SharedData sd) {
        this.sd = sd;
        synchronized (sd) {
            array = sd.getArray();
            b = sd.getB();
        }
        winArray = new boolean[array.size()];
    }

    /**
     * Recursively searches the first {@code n} values for the remaining target.
     *
     * @param n number of values currently considered
     * @param b remaining target value
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        if (n == 1) {
            if(b == 0 || b == array.get(n - 1)) {
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
            if (b == array.get(n - 1)) winArray[n - 1] = true;
            return;
        }

        rec(n - 1, b - array.get(n - 1));
        if (flag) winArray[n - 1] = true;
        synchronized (sd) {
            if (sd.getFlag()) return;
        }
        rec(n - 1, b);
    }

    /**
     * Executes this worker's search and publishes the solution flags.
     */
    @Override
    public void run() {
        if (array.size() != 1)
            if (Thread.currentThread().getName().equals("thread1"))
                rec(array.size() - 1, b - array.get(array.size() - 1));
            else
                rec(array.size() - 1, b);
        if (array.size() == 1)
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) { sd.setFlag(true); }
            }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1"))
                winArray[array.size() - 1] = true;
            synchronized (sd) { sd.setWinArray(winArray); }
        }
    }
}
