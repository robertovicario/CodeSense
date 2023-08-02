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
 * The BashRunner class is responsible for executing Bash code and measuring its performance.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class BashRunner extends RunnerBase {

    /**
     * Constructs a new BashRunner object with the specified Bash source code.
     *
     * @param code The Bash source code to be executed by the runner.
     */
    public BashRunner(String code) {
        super(code);
    }

    /**
     * Creates a new Bash process using the "bash" command.
     *
     * @return The created Bash process.
     * @throws IOException If an I/O error occurs while starting the process.
     */
    private Process createBashProcess() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("bash");
        return processBuilder.start();
    }

    /**
     * Executes the Bash script in the provided process.
     *
     * @param process The Bash process to execute the script in.
     * @throws IOException If an I/O error occurs while writing the script to the process.
     * @throws InterruptedException If the execution of the Bash script is interrupted.
     */
    private void executeBashScript(Process process) throws IOException, InterruptedException {
        try (OutputStream outputStream = process.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.println(code);
        }
    }

    /**
     * Retrieves the used memory by the JVM after the Bash script execution.
     *
     * @return The used memory in kilobytes.
     */
    private long getUsedMemoryAfterExecution() {
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        return memoryUsage.getUsed() / 1024;
    }

    /**
     * Computes the execution time of the Bash code.
     *
     * @return The execution time of the Bash code in milliseconds.
     * @throws LangException If an error occurs during Bash script execution.
     */
    public long computeTime() {
        long startTime = System.currentTimeMillis();

        try {
            Process process = createBashProcess();
            executeBashScript(process);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new LangException("Bash script execution failed.");
            }

            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }

    /**
     * Computes the memory usage of the Bash code.
     *
     * @return The memory usage of the Bash code in kilobytes.
     * @throws LangException If an error occurs during Bash script execution.
     */
    public long computeSpace() {
        try {
            Process process;
            ProcessBuilder processBuilder = new ProcessBuilder("bash", "-c",
                    "echo \"" + code + "\" | bash -x");
            process = processBuilder.start();
            process.waitFor();

            return getUsedMemoryAfterExecution();
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }
}
