package src.core;

/**
 * The Core interface defines methods to run, optimize, and manage code-related tasks.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public interface Core {
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
     * Clears the table.
     */
    void clearTable();
}
