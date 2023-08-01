package src.core.lang;

import src.errors.CoreException;
import src.errors.LangException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;
import java.nio.charset.StandardCharsets;

public class PythonCore extends CoreBase {
    public PythonCore(String code) {
        super(code);
    }

    public long computeTime() {
        long startTime = System.currentTimeMillis();
        long result = 0;

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python");
            Process process = processBuilder.start();
            OutputStream outputStream = process.getOutputStream();
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            writer.println(code);
            writer.close();

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                long endTime = System.currentTimeMillis();
                result = endTime - startTime;
            } else {
                throw new CoreException("Python script execution failed");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }

    public long computeSpace() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "-c",
                    "import tracemalloc; tracemalloc.start(); exec('''" + code + "''');");
            Process process = processBuilder.start();
            process.waitFor();

            MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
            return memoryUsage.getUsed();
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }
}
