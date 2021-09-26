package ru.adamDev.callCenter.apiJson;

import lombok.Data;
import ru.adamDev.callCenter.service.enums.TaskStatus;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * Incoming JSON for filter search
 */
@Data
public class TaskFilter {

    @NotBlank
    private Date startDate;
    @NotBlank
    private Date finishDate;
    private Long number;
    private TaskStatus status;
}
