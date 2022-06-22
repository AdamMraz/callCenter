package ru.adamDev.callCenter.service.response.hendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.adamDev.callCenter.service.annotation.TaskExceptionHandler;
import ru.adamDev.callCenter.service.exception.TaskException;
import ru.adamDev.callCenter.service.response.ResponseBody;

import java.util.Date;

@RestControllerAdvice
@ControllerAdvice (annotations = TaskExceptionHandler.class)
public class TaskExceptionHandlerController {

    @ExceptionHandler(TaskException.class)
    public ResponseEntity taskException(TaskException e) {
        return new ResponseEntity(new ResponseBody(new Date().getTime(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
