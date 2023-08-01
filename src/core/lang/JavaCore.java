package src.core.lang;

import src.errors.CoreException;
import src.errors.LangException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JavaCore extends CoreBase {
    public JavaCore(String code) {
        super(code);
    }

    public long computeTime() {
        long startTime = System.currentTimeMillis();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("javac", "-d", "classes", "-sourcepath", "src", "-");
            Process process = processBuilder.start();
            process.getOutputStream().write(code.getBytes());
            process.getOutputStream().close();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new CoreException("Java code compilation failed");
            }

            ProcessBuilder runProcessBuilder = new ProcessBuilder("java", "-cp", "classes", "Main");
            Process runProcess = runProcessBuilder.start();
            runProcess.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public long computeSpace() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("javac", "-d", "classes", "-sourcepath", "src", "-");
            Process process = processBuilder.start();
            process.getOutputStream().write(code.getBytes());
            process.getOutputStream().close();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new CoreException("Java code compilation failed");
            }

            ProcessBuilder runProcessBuilder = new ProcessBuilder("java", "-cp", "classes", "Main");
            Process runProcess = runProcessBuilder.start();

            runProcess.waitFor();
            InputStream inputStream = runProcess.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            long maxMemoryUsed = 0;
            while ((line = reader.readLine()) != null) {
                long memoryUsed = Long.parseLong(line);
                if (memoryUsed > maxMemoryUsed) {
                    maxMemoryUsed = memoryUsed;
                }
            }
            return maxMemoryUsed;
        } catch (IOException | InterruptedException e) {
            throw new LangException(e.getMessage());
        }
    }
}
