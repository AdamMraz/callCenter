package ru.adamDev.callCenter.service.service.impl;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.repo.TaskRepo;
import ru.adamDev.callCenter.service.annotation.LogAutowired;
import ru.adamDev.callCenter.service.exception.TaskFilterDateIsNullException;
import ru.adamDev.callCenter.service.exception.TaskIllegalNumberException;
import ru.adamDev.callCenter.service.exception.TaskIsExistException;
import ru.adamDev.callCenter.service.exception.TaskNotFoundException;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.service.service.TaskManagerService;

import java.util.Date;
import java.util.List;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

    @LogAutowired
    private Logger LOG;
    private final TaskRepo taskRepo;

    @Autowired
    public TaskManagerServiceImpl(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public TaskManagerServiceImpl(TaskRepo taskRepo, Logger log) {
        this.taskRepo = taskRepo;
        this.LOG = log;
    }

//    public void setLOG (Logger log) {
//        this.LOG = log;
//    }

    /**
     * Save new Task. Cannot save task with number <= 0.
     *
     * @param task Task to save
     * @throws TaskIsExistException If current Task is exist
     */
    @Override
    public void saveTask(Task task) throws TaskIsExistException, TaskIllegalNumberException {

        if (task.getNumber() <= 0) {
            LOG.warn("The task number cannot be a negative number or 0");
            throw new TaskIllegalNumberException("The task number cannot be a negative number or 0");
        }
        if (taskRepo.findByNumber(task.getNumber()) != null) {
            LOG.warn("A task with this number already exists");
            throw new TaskIsExistException("A task with this number already exists");
        }

        taskRepo.save(task);
    }

    @Override
    public List<Task> finedTasks(TaskFilter filter) throws TaskFilterDateIsNullException {

        if (filter.getStartDate() == null || filter.getFinishDate() == null) {
            LOG.warn("The start and end dates cannot be empty");
            throw new TaskFilterDateIsNullException("The start and end dates cannot be empty");
        }

        return taskRepo.findByFilter(filter.getStartDate(),
                filter.getFinishDate(),
                filter.getNumber(),
                filter.getStatus());
    }

    @Override
    public void updateTask(UpdateTask updateTaskModel) throws TaskIllegalNumberException, TaskNotFoundException {

        if (updateTaskModel.getTaskId() <= 0) {
            LOG.warn("The task number cannot be a negative number or 0");
            throw new TaskIllegalNumberException("The task number cannot be a negative number or 0");
        }

        Task task = taskRepo.findById(updateTaskModel.getTaskId()).orElse(null);

        if (task == null) {
            LOG.warn("The task with current id not fount");
            throw new TaskNotFoundException("The task with current id not fount");
        }

        if (updateTaskModel.getStatus() != null)
            task.setStatus(updateTaskModel.getStatus());
        if (updateTaskModel.getComment() != null)
            task.setComment(updateTaskModel.getComment());
        task.setUpdateDate(new Date());

        taskRepo.save(task);
    }
}
