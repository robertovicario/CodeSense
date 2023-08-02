package src.core.runner;

import src.core.runner.lang.*;
import src.errors.RunnerException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A concrete implementation of the CodeRunner interface.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class RunnerImpl implements Runner {
    private final DefaultComboBoxModel<String> defaultComboBoxModel;
    private final JTextArea jTextArea;
    private final DefaultTableModel defaultTableModel;
    private int ID = 1;

    /**
     * Constructs a new CodeRunnerImpl instance with the provided components.
     *
     * @param defaultComboBoxModel The combo box model containing the supported programming languages.
     * @param jTextArea The text area where the code to be executed is entered.
     * @param defaultTableModel The defaultTableModel model where the results are displayed.
     */
    public RunnerImpl(DefaultComboBoxModel<String> defaultComboBoxModel, JTextArea jTextArea, DefaultTableModel defaultTableModel) {
        this.defaultComboBoxModel = defaultComboBoxModel;
        this.jTextArea = jTextArea;
        this.defaultTableModel = defaultTableModel;
    }

    /**
     * Runs the code entered the JTextArea using the selected programming language.
     * The execution time and space are computed, and the results are displayed in a defaultTableModel.
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
     * This method implements the logic to export data from a JTable to a CSV file.
     */
    @Override
    public void export() {
        JFileChooser jFileChooser = new JFileChooser();
        int userSelection = jFileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = jFileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".csv")) {
                filePath += ".csv";
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                int rowCount = defaultTableModel.getRowCount();
                int colCount = defaultTableModel.getColumnCount();

                for (int i = 0; i < colCount; i++) {
                    writer.append(defaultTableModel.getColumnName(i));
                    if (i < colCount - 1) {
                        writer.append(",");
                    } else {
                        writer.append("\n");
                    }
                }

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        Object value = defaultTableModel.getValueAt(row, col);
                        if (value != null) {
                            writer.append(value.toString());
                        }
                        if (col < colCount - 1) {
                            writer.append(",");
                        } else {
                            writer.append("\n");
                        }
                    }
                }

                JOptionPane.showMessageDialog(null, "Data exported successfully.", getClass().toString(), JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                throw new RunnerException(e.getMessage());
            }
        }
    }


    /**
     * TODO: Implement the comparison logic here
     */
    @Override
    public void compare() {
        // TODO: Implement the comparison logic here
    }

    /**
     * Clears the defaultTableModel containing the results of executed code.
     */
    @Override
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }
}
