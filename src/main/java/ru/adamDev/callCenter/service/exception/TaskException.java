package ru.adamDev.callCenter.service.exception;

/**
 * Task exception superclass
 */
public class TaskException extends Exception {

    public TaskException() {
        super();
    }

    public TaskException (String msg) {
        super(msg);
    }
}
