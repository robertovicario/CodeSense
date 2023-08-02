package src.app.layout;

import javax.swing.*;
import java.awt.*;

/**
 * The Optimization class represents the comparison GUI.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class Optimization extends JFrame {
    /**
     * Constructs a new instance of the Optimization class.
     */
    public Optimization() {
        initComponents();
        initEvents();
    }

    /**
     *
     */
    public void initComponents() {
        setTitle("CodeSense - Optimization");
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
