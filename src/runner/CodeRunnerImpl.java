package src.runner;

import src.runner.lang.*;
import src.errors.RunnerException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A concrete implementation of the CodeRunner interface.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class CodeRunnerImpl implements CodeRunner {
    private final DefaultComboBoxModel<String> defaultComboBoxModel;
    private final JTextArea jTextArea;
    private final DefaultTableModel defaultTableModel;
    private int ID = 1;

    /**
     * Constructs a new CodeRunnerImpl instance with the provided components.
     *
     * @param defaultComboBoxModel The combo box model containing the supported programming languages.
     * @param jTextArea The text area where the code to be executed is entered.
     * @param defaultTableModel The table model where the results are displayed.
     */
    public CodeRunnerImpl(DefaultComboBoxModel<String> defaultComboBoxModel, JTextArea jTextArea, DefaultTableModel defaultTableModel) {
        this.defaultComboBoxModel = defaultComboBoxModel;
        this.jTextArea = jTextArea;
        this.defaultTableModel = defaultTableModel;
    }

    /**
     * Runs the code entered the JTextArea using the selected programming language.
     * The execution time and space are computed, and the results are displayed in a table.
     *
     * @throws RunnerException If no programming language is selected or the selected language is unsupported.
     */
    @Override
    public void run() {
        String selectedLanguage = defaultComboBoxModel.getSelectedItem().toString();

        if (selectedLanguage.equals("-")) {
            throw new RunnerException("Please choose a programming language.");
        }

        RunnerBase core = switch (selectedLanguage) {
            case "Bash" -> new BashRunner(jTextArea.getText());
            case "JavaScript" -> new JavaScriptRunner(jTextArea.getText());
            case "Python" -> new PythonRunner(jTextArea.getText());
            default -> throw new RunnerException("Unsupported programming language.");
        };

        long time = core.computeTime();
        long space = core.computeSpace();

        defaultTableModel.addRow(new String[]{String.valueOf(ID), selectedLanguage, String.valueOf(time), String.valueOf(space)});
        defaultTableModel.moveRow(defaultTableModel.getRowCount() - 1, defaultTableModel.getRowCount() - 1, 0);
        defaultTableModel.fireTableDataChanged();
        ID++;
    }

    /**
     * TODO: Implement the optimization logic here
     */
    @Override
    public void optimize() {
        // TODO: Implement the optimization logic here
    }

    /**
     * Clears the JTextArea where the code is entered.
     */
    @Override
    public void clearTextArea() {
        jTextArea.setText("");
    }

    /**
     * TODO: Implement the export logic here
     */
    @Override
    public void export() {
        // TODO: Implement the export logic here
    }

    /**
     * TODO: Implement the comparison logic here
     */
    @Override
    public void compare() {
        // TODO: Implement the comparison logic here
    }

    /**
     * Clears the table containing the results of executed code.
     */
    @Override
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }
}
