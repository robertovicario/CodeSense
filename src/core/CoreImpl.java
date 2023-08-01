package src.core;

import src.core.lang.*;
import src.errors.CoreException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CoreImpl implements Core {
    DefaultComboBoxModel<String> defaultComboBoxModel;
    JTextArea jTextArea;
    DefaultTableModel defaultTableModel;
    int ID = 0;

    public CoreImpl(DefaultComboBoxModel<String> defaultComboBoxModel, JTextArea jTextArea, DefaultTableModel defaultTableModel) {
        this.defaultComboBoxModel = defaultComboBoxModel;
        this.jTextArea = jTextArea;
        this.defaultTableModel = defaultTableModel;
    }

    @Override
    public void run() {
        String language = defaultComboBoxModel.getSelectedItem().toString();

        if (language.equals("-")) {
            throw new CoreException("Please choose a programming language.");
        }

        long time;
        long space;
        CoreBase core = switch (language) {
            case "C" -> new CCore(jTextArea.getText());
            case "Java" -> new JavaCore(jTextArea.getText());
            case "Python" -> new PythonCore(jTextArea.getText());
            default -> throw new CoreException("Unsupported programming language: " + language);
        };

        time = core.computeTime();
        space = core.computeSpace();
        defaultTableModel.addRow(new Object[]{ID, language, time, space});
        defaultTableModel.moveRow(defaultTableModel.getRowCount() - 1, defaultTableModel.getRowCount() - 1, 0);
        defaultTableModel.fireTableDataChanged();
        ID++;
    }

    @Override
    public void optimize() {

    }

    @Override
    public void clearTextArea() {
        jTextArea.setText("");
    }

    @Override
    public void export() {

    }

    @Override
    public void compare() {

    }

    @Override
    public void clearTable() {
        defaultTableModel.setRowCount(0);
    }
}
