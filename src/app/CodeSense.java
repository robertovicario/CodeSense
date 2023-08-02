package src.app;

import com.formdev.flatlaf.FlatLightLaf;
import src.app.layout.App;

import javax.swing.*;

/**
 * The CodeSense class is the entry point of the application.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class CodeSense {
    /**
     * The main method that starts the application.
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e.getMessage());
        }

        new App();
    }
}
