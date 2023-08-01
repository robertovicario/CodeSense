package src.errors;

import javax.swing.*;

public class CoreException extends RuntimeException {
    public CoreException(String e) {
        JOptionPane.showMessageDialog(null, e, getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }
}
