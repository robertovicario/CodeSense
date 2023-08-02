package src.core.runner;

/**
 * The CodeRunner interface defines methods to run, optimize, and manage code-related tasks.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public interface Runner {
    /**
     * Runs the code.
     */
    void run();

    /**
     * Optimizes the code.
     */
    void optimize();

    /**
     * Clears the text area.
     */
    void clearTextArea();

    /**
     * Exports the code or its results.
     */
    void export();

    /**
     * Compares the code or its results with expected values.
     */
    void compare();

    /**
     * Clears the table.
     */
    void clearTable();
}
