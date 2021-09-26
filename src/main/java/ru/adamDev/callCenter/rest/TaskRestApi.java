package ru.adamDev.callCenter.rest;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.adamDev.callCenter.apiJson.NewTask;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.annotation.TaskExceptionHandler;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.service.response.ResponseBody;
import ru.adamDev.callCenter.service.service.TaskManagerService;

import java.util.Collections;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "The addition was completed successfully.",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(value =
                                                    "{" +
                                                    "\n\t\"timestamp\":1632656398216," +
                                                    "\n\t\"statusCode\":200," +
                                                    "\n\t\"status\":\"OK\"," +
                                                    "\n\t\"message\":\"Success\"" +
                                                    "\n}")
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(name = "Illegal number",
                                                    description = "The task number cannot be a negative number or 0.",
                                                    value =
                                                    "{\n\t" +
                                                    "\"timestamp\":1632662317699," +
                                                    "\n\t\"statusCode\":400," +
                                                    "\n\t\"status\":\"Bad Request\"," +
                                                    "\n\t\"message\":\"The task number cannot be a negative number or 0\"" +
                                                    "\n}"),
                                            @ExampleObject(name = "Task is exist",
                                                    description = "A task with this number already exists.",
                                                    value =
                                                    "{" +
                                                    "\n\t\"timestamp\":1632662580022," +
                                                    "\n\t\"statusCode\":400," +
                                                    "\n\t\"status\":\"Bad Request\"," +
                                                    "\n\t\"message\":\"A task with this number already exists\"" +
                                                    "\n}")
                                    }
                            )
                    }
            )
    })
    @SneakyThrows
    @PostMapping("/add")
    public ResponseEntity add (@RequestBody NewTask requestBody) {

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(value =
                                                    "{" +
                                                    "\n\t\"timestamp\":1632665596284," +
                                                    "\n\t\"statusCode\":200,\n\t\"status\":\"OK\",\n\t\"message\":[" +
                                                    "\n\t\t{" +
                                                    "\n\t\t\t\"taskId\":26,\n\t\t\"number\":2," +
                                                    "\n\t\t\t\"createDate\":\"2021-09-26T07:56:43.789+00:00\"," +
                                                    "\n\t\t\t\"updateDate\":null," +
                                                    "\n\t\t\t\"status\":\"NOT_COMPLETED\"," +
                                                    "\n\t\t\t\"comment\":\"\"" +
                                                    "\n\t\t}," +
                                                    "\n\t\t{" +
                                                    "\n\t\t\t\"taskId\":27," +
                                                    "\n\t\t\t\"number\":3," +
                                                    "\n\t\t\t\"createDate\":\"2021-09-26T07:56:43.792+00:00\"" +
                                                    "\n\t\t\t,\"updateDate\":null," +
                                                    "\n\t\t\t\"status\":\"NOT_COMPLETED\"," +
                                                    "\n\t\t\t\"comment\":\"\"" +
                                                    "\n\t\t}" +
                                                    "\n\t]" +
                                                    "\n}")
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400",
                    description = "The start and end dates cannot be empty.",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(value =
                                                    "{" +
                                                    "\n\t\"timestamp\":\"2021-09-26T14:20:17.599+00:00\"," +
                                                    "\n\t\"statusCode\":400," +
                                                    "\n\t\"status\":\"Bad Request\"," +
                                                    "\n\t\"message\":\"The start and end dates cannot be empty\"" +
                                                    "\n}")
                                    }
                            )
                    }
            )
    })
    @SneakyThrows
    @PostMapping("/get")
    public ResponseEntity get (@RequestBody TaskFilter filter) {

        return new ResponseEntity(new ResponseBody(
                new Date().getTime(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                taskManagerService.finedTasks(filter)), HttpStatus.OK);

//        return new ResponseEntity(taskManagerService.finedTasks(filter), HttpStatus.OK);
    }

    /**
     * Update task
     *
     * @param requestBody Incoming JSON for updating the task
     * @see ru.adamDev.callCenter.apiJson.UpdateTask
     */
    @SneakyThrows
    @PostMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400",
                    description = "The update was completed successfully",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(value =
                                                    "{" +
                                                    "\n\t\"timestamp\":1632666526827," +
                                                    "\n\t\"statusCode\":200," +
                                                    "\n\t\"status\":\"OK\"," +
                                                    "\n\t\"message\":\"Success\"" +
                                                    "\n}")
                                    }
                            )
                    }
            ),
            @ApiResponse(responseCode = "400",
                    content = {
                            @Content(schema = @Schema(implementation = ResponseBody.class),
                                    examples = {
                                            @ExampleObject(name = "Illegal number",
                                                    description = "The task number cannot be a negative number or 0.",
                                                    value =
                                                            "{\n\t" +
                                                            "\"timestamp\":1632662317699," +
                                                            "\n\t\"statusCode\":400," +
                                                            "\n\t\"status\":\"Bad Request\"," +
                                                            "\n\t\"message\":\"The task number cannot be a negative number or 0\"" +
                                                            "\n}"),
                                            @ExampleObject(name = "Task not found",
                                                    description = "The task with current id not fount.",
                                                    value =
                                                            "{" +
                                                            "\n\t\"timestamp\":1632666723009," +
                                                            "\n\t\"statusCode\":400," +
                                                            "\n\t\"status\":\"Bad Request\"," +
                                                            "\n\t\"message\":\"The task with current id not fount\"" +
                                                            "\n}")
                                    }
                            )
                    }
            )
    })
    public ResponseEntity update (@RequestBody UpdateTask requestBody) {

        taskManagerService.updateTask(requestBody);

        return new ResponseEntity(new ResponseBody(
                new Date().getTime(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Success"), HttpStatus.OK);
    }
}
