package ru.adamDev.callCenter.apiJson;

import lombok.Data;
import ru.adamDev.callCenter.service.enums.TaskStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Incoming JSON for updating the task
 */
@Data
public class UpdateTask {

    @NotBlank
    @Size(min = 1)
    private long taskId;
    private TaskStatus status;
    private String comment;
}
