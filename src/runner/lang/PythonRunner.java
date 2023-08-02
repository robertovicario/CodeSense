package src.runner.lang;

import src.errors.LangException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.nio.charset.StandardCharsets;

public class PythonRunner extends RunnerBase {
    public PythonRunner(String code) {
        super(code);
    }

    private Process createPythonProcess() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("python");
        return processBuilder.start();
    }

    private void executePythonScript(Process process) throws IOException, InterruptedException {
        try (OutputStream outputStream = process.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            writer.println(code);
        }
    }

    private long getUsedMemoryAfterExecution() {
        MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        return memoryUsage.getUsed() / 1024;
    }

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
