package ru.adamDev.callCenter.service.exception;

/**
 * It is thrown out if it is not found
 */
public class TaskNotFoundException extends TaskException {

    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException (String msg) {
        super(msg);
    }
}
