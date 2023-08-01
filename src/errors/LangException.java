package src.errors;

import javax.swing.*;

public class LangException extends RuntimeException {
    public LangException(String e) {
        JOptionPane.showMessageDialog(null, e, getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }
}
