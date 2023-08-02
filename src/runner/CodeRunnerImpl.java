package src.runner;

import src.runner.lang.*;
import src.errors.RunnerException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CodeRunnerImpl implements CodeRunner {
    private final DefaultComboBoxModel<String> defaultComboBoxModel;
    private final JTextArea jTextArea;
    private final DefaultTableModel defaultTableModel;
    private int ID = 1;

    public CodeRunnerImpl(DefaultComboBoxModel<String> defaultComboBoxModel, JTextArea jTextArea, DefaultTableModel defaultTableModel) {
        this.defaultComboBoxModel = defaultComboBoxModel;
        this.jTextArea = jTextArea;
        this.defaultTableModel = defaultTableModel;
    }

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

    @Override
    public void optimize() {
        // TODO: Implement the optimization logic here
    }

    @Override
    public void clearTextArea() {
        jTextArea.setText("");
    }

    @Override
    public void export() {
        // TODO: Implement the export logic here
    }

    @Override
    public void compare() {
        // TODO: Implement the comparison logic here
    }

    @Override
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }
}
