package src.errors;

import javax.swing.*;

/**
 * The LangException class represents errors related to languages.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class LangException extends RuntimeException {
    /**
     * Constructs a new instance of the LangException class with the given error message.
     *
     * @param errorMessage The error message to be displayed when the exception is thrown.
     */
    public LangException(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }
}
