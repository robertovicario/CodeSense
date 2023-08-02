package src.runner.lang;

/**
 * The RunnerBase class is an abstract base class for implementing code runners.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public abstract class RunnerBase {

    /**
     * The source code to be executed by the runner.
     */
    protected String code;

    /**
     * Constructs a new RunnerBase object with the specified source code.
     *
     * @param code The source code to be executed by the runner.
     */
    public RunnerBase(String code) {
        this.code = code;
    }

    /**
     * Computes the execution time of the code.
     *
     * @return The execution time of the code in milliseconds.
     */
    public abstract long computeTime();

    /**
     * Computes the space (memory) usage of the code.
     *
     * @return The space (memory) usage of the code in bytes.
     */
    public abstract long computeSpace();
}
