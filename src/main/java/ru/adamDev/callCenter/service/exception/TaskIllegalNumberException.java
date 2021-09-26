package ru.adamDev.callCenter.service.exception;

/**
 * It is thrown if the task number is 0 or a negative number
 */
public class TaskIllegalNumberException extends TaskException {

    public TaskIllegalNumberException() {
        super();
    }

    public TaskIllegalNumberException(String msg) {
        super(msg);
    }
}
