package ru.adamDev.callCenter.service.exception;

/**
 * It is thrown out if a task with such an order number already exists
 */
public class TaskIsExistException extends TaskException {

    public TaskIsExistException() {
        super();
    }

    public TaskIsExistException(String msg) {
        super(msg);
    }
}
