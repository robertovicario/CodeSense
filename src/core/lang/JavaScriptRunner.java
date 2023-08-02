package src.core.lang;

import src.errors.LangException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.nio.charset.StandardCharsets;

/**
 * The JavaScriptRunner class is responsible for executing JavaScript code and measuring its performance.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class JavaScriptRunner extends RunnerBase {

    /**
     * Constructs a new JavaScriptRunner object with the specified JavaScript source code.
     *
     * @param code The JavaScript source code to be executed by the runner.
     */
    public JavaScriptRunner(String code) {
        super(code);
    }

    /**
     * Creates a new Node.js process using the "node" command.
     *
     * @return The created Node.js process.
     * @throws IOException If an I/O error occurs while starting the process.
     */
    private Process createNodeProcess() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("node");
        return processBuilder.start();
    }

    /**
     * Executes the JavaScript script in the provided process.
     *
     * @param process The Node.js process to execute the script in.
     * @throws IOException If an I/O error occurs while writing the script to the process.
     * @throws InterruptedException If the execution of the JavaScript script is interrupted.
     */
    private void executeJavaScriptScript(Process process) throws IOException, InterruptedException {
        try (OutputStream outputStream = process.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.println(code);
        }
    }

    /**
     * Retrieves the used memory by the JVM after the JavaScript script execution.
     *
     * @return The used memory in kilobytes.
     */
    private long getUsedMemoryAfterExecution() {
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        return memoryUsage.getUsed() / 1024;
    }

    /**
     * Computes the execution time of the JavaScript code.
     *
     * @return The execution time of the JavaScript code in milliseconds.
     * @throws LangException If an error occurs during JavaScript script execution.
     */
    public long computeTime() {
        long startTime = System.currentTimeMillis();

        try {
            Process process = createNodeProcess();
            executeJavaScriptScript(process);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new LangException("JavaScript script execution failed.");
            }

            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }

    /**
     * Computes the memory usage of the JavaScript code.
     *
     * @return The memory usage of the JavaScript code in kilobytes.
     * @throws LangException If an error occurs during JavaScript script execution.
     */
    public long computeSpace() {
        try {
            Process process;
            ProcessBuilder processBuilder = new ProcessBuilder("node", "--expose-gc", "-e",
                    code);
            process = processBuilder.start();
            process.waitFor();

            return getUsedMemoryAfterExecution();
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }
}
