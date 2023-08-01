package src.core;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CoreImpl implements Core {
    JTextArea jTextArea;
    JTable jTable;
    DefaultTableModel defaultTableModel = (DefaultTableModel) jTable.getModel();

    public CoreImpl(JTextArea jTextArea, JTable jTable) {
        this.jTextArea = jTextArea;
        this.jTable = jTable;
    }

    @Override
    public void run() {

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
