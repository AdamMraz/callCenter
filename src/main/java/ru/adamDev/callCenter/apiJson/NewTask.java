package ru.adamDev.callCenter.apiJson;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Incoming JSON to create a new task
 */
@Data
public class NewTask {
    @NotBlank
    @Size(min = 1)
    private long number;
}
