package src.app.layout;

import javax.swing.*;
import java.awt.*;

/**
 * The Comparison class represents the comparison GUI.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class Comparison extends JFrame {
    /**
     * Constructs a new instance of the Comparison class.
     */
    public Comparison() {
        initComponents();
        initEvents();
    }

    /**
     *
     */
    public void initComponents() {
        setTitle("CodeSense - Comparison");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     *
     */
    public void initEvents() {

    }
}
