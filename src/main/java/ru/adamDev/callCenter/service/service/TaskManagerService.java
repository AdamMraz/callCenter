package ru.adamDev.callCenter.service.service;

import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.exception.TaskFilterDateIsNullException;
import ru.adamDev.callCenter.service.exception.TaskIllegalNumberException;
import ru.adamDev.callCenter.service.exception.TaskIsExistException;
import ru.adamDev.callCenter.service.exception.TaskNotFoundException;
import ru.adamDev.callCenter.apiJson.TaskFilter;

import java.util.List;

public interface TaskManagerService {

    /**
     * Save a new task
     *
     * @param task Task to save
     * @throws TaskIsExistException It is thrown out if a task with such an order number already exists
     * @throws TaskIllegalNumberException It is thrown if the task number is 0 or a negative number
     */
    void saveTask (Task task) throws TaskIsExistException, TaskIllegalNumberException;

    /**
     * Getting tasks by filters
     *
     * @param filter Filters
     * @return List of found tasks
     * @throws TaskFilterDateIsNullException It is thrown out if the filter does not have an initial and/or final date
     */
    List<Task> finedTasks (TaskFilter filter) throws TaskFilterDateIsNullException;

    /**
     * Update task
     *
     * @param updateTaskModel Param to update task
     * @throws TaskIllegalNumberException It is thrown if the task number is 0 or a negative number
     * @throws TaskNotFoundException It is thrown out if it is not found
     */
    void updateTask (UpdateTask updateTaskModel) throws TaskIllegalNumberException, TaskNotFoundException;
}
