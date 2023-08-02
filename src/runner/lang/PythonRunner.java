package src.runner.lang;

import src.errors.LangException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.nio.charset.StandardCharsets;

/**
 * The PythonRunner class is responsible for executing Python code and measuring its performance.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class PythonRunner extends RunnerBase {

    /**
     * Constructs a new PythonRunner object with the specified Python source code.
     *
     * @param code The Python source code to be executed by the runner.
     */
    public PythonRunner(String code) {
        super(code);
    }

    /**
     * Creates a new Python process using the "python" command.
     *
     * @return The created Python process.
     * @throws IOException If an I/O error occurs while starting the process.
     */
    private Process createPythonProcess() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python");
        return processBuilder.start();
    }

    /**
     * Executes the Python script in the provided process.
     *
     * @param process The Python process to execute the script in.
     * @throws IOException If an I/O error occurs while writing the script to the process.
     * @throws InterruptedException If the execution of the Python script is interrupted.
     */
    private void executePythonScript(Process process) throws IOException, InterruptedException {
        try (OutputStream outputStream = process.getOutputStream();
             PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.println(code);
        }
    }

    /**
     * Retrieves the used memory by the JVM after the Python script execution.
     *
     * @return The used memory in kilobytes.
     */
    private long getUsedMemoryAfterExecution() {
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        return memoryUsage.getUsed() / 1024;
    }

    /**
     * Computes the execution time of the Python code.
     *
     * @return The execution time of the Python code in milliseconds.
     * @throws LangException If an error occurs during Python script execution.
     */
    public long computeTime() {
        long startTime = System.currentTimeMillis();

        try {
            Process process = createPythonProcess();
            executePythonScript(process);

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new LangException("Python script execution failed.");
            }

            long endTime = System.currentTimeMillis();
            return endTime - startTime;
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }

    /**
     * Computes the memory usage of the Python code.
     *
     * @return The memory usage of the Python code in kilobytes.
     * @throws LangException If an error occurs during Python script execution.
     */
    public long computeSpace() {
        try {
            Process process;
            ProcessBuilder processBuilder = new ProcessBuilder("python", "-c",
                    "import tracemalloc; tracemalloc.start(); exec('''" + code + "''');");
            process = processBuilder.start();
            process.waitFor();

            return getUsedMemoryAfterExecution();
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }
}
