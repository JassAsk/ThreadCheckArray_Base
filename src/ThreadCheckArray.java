import java.util.ArrayList;

/**
 * Searches a portion of the input values for a subset matching the target sum.
 */
public class ThreadCheckArray implements Runnable {
    private boolean flag;
    private boolean[] winArray;
    private SharedData sd;
    private ArrayList<Integer> array;
    private int b;

    /**
     * Creates a worker using the supplied shared search data.
     *
     * @param sd shared values, target, and result state
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
     * Recursively searches the first {@code n} values for a subset totaling
     * {@code b}.
     *
     * @param n number of values in the current search range
     * @param b remaining sum to match
     */
    void rec(int n, int b) {
        synchronized (sd) {
            if (sd.getFlag()) {
                return;
            }
        }
        if (n == 1) {
            if (b == 0 || b == array.get(n - 1)) {
                flag = true;
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
            if (b == array.get(n - 1)) {
                winArray[n - 1] = true;
            }
            return;
        }

        rec(n - 1, b - array.get(n - 1));
        if (flag) {
            winArray[n - 1] = true;
        }
        synchronized (sd) {
            if (sd.getFlag()) {
                return;
            }
        }
        rec(n - 1, b);
    }

    /**
     * Runs the worker's portion of the subset search and publishes its result
     * if it finds a match.
     */
    @Override
    public void run() {
        if (array.size() != 1) {
            if (Thread.currentThread().getName().equals("thread1")) {
                rec(array.size() - 1, b - array.get(array.size() - 1));
            } else {
                rec(array.size() - 1, b);
            }
        }
        if (array.size() == 1) {
            if (b == array.get(0) && !flag) {
                winArray[0] = true;
                flag = true;
                synchronized (sd) {
                    sd.setFlag(true);
                }
            }
        }
        if (flag) {
            if (Thread.currentThread().getName().equals("thread1")) {
                winArray[array.size() - 1] = true;
            }
            synchronized (sd) {
                sd.setWinArray(winArray);
            }
        }
    }
}
