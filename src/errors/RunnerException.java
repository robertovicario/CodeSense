package src.errors;

import javax.swing.*;

/**
 * The RunnerException class represents errors related to runners.
 *
 * @author Roberto Vicario
 * @version 1.0
 */
public class RunnerException extends RuntimeException {
    /**
     * Constructs a new instance of the RunnerException class with the given error message.
     *
     * @param errorMessage The error message to be displayed when the exception is thrown.
     */
    public RunnerException(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, getClass().toString(), JOptionPane.ERROR_MESSAGE);
    }
}
