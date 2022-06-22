package ru.adamDev.callCenter.rest;

import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.adamDev.callCenter.apiJson.NewTask;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.annotation.ApiResponses.TaskAddApiResponses;
import ru.adamDev.callCenter.service.annotation.ApiResponses.TaskGetApiResponses;
import ru.adamDev.callCenter.service.annotation.ApiResponses.TaskUpdateApiResponses;
import ru.adamDev.callCenter.service.annotation.TaskExceptionHandler;
import ru.adamDev.callCenter.service.response.ResponseBody;
import ru.adamDev.callCenter.service.service.TaskManagerService;

import java.util.Date;

/**
 * Api for working with tasks
 *
 * @see ru.adamDev.callCenter.service.annotation.TaskExceptionHandler
 * @see ru.adamDev.callCenter.service.response.hendler.TaskExceptionHandlerController TaskExceptionHandlerController
 */
@RestController
@RequestMapping(value = "/api/v1/task")
@TaskExceptionHandler
public class TaskRestApi {

    private final TaskManagerService taskManagerService;

    public TaskRestApi(TaskManagerService taskManagerService) {
        this.taskManagerService = taskManagerService;
    }

    /**
     * Creating a new task
     *
     * @param requestBody Incoming JSON to create a new task
     * @see ru.adamDev.callCenter.apiJson.NewTask
     */
    @TaskAddApiResponses
    @SneakyThrows
    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity add(@RequestBody NewTask requestBody) {
        Task task = new Task(requestBody.getNumber());
        taskManagerService.saveTask(task);
        return new ResponseEntity(new ResponseBody(
                new Date().getTime(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Success"), HttpStatus.OK);
    }

    /**
     * Getting tasks by filters
     *
     * @param filter Incoming JSON for filter search
     * @see ru.adamDev.callCenter.apiJson.TaskFilter
     */
    @TaskGetApiResponses
    @SneakyThrows
    @PostMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity get(@RequestBody TaskFilter filter) {
        return new ResponseEntity(new ResponseBody(
                new Date().getTime(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                taskManagerService.finedTasks(filter)), HttpStatus.OK);
    }

    /**
     * Update task
     *
     * @param requestBody Incoming JSON for updating the task
     * @see ru.adamDev.callCenter.apiJson.UpdateTask
     */
    @TaskUpdateApiResponses
    @SneakyThrows
    @PostMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody UpdateTask requestBody) {
        taskManagerService.updateTask(requestBody);
        return new ResponseEntity(new ResponseBody(
                new Date().getTime(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Success"), HttpStatus.OK);
    }
}
