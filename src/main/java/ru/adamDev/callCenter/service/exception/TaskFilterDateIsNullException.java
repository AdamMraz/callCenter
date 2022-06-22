package ru.adamDev.callCenter.service.exception;

/**
 * It is thrown out if the filter does not have an initial and/or final date
 */
public class TaskFilterDateIsNullException extends TaskException {

    public TaskFilterDateIsNullException() {
        super();
    }

    public TaskFilterDateIsNullException(String msg) {
        super(msg);
    }
}
