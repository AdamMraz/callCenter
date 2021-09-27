package ru.adamDev.callCenter.springTest.service.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.repo.TaskRepo;
import ru.adamDev.callCenter.service.enums.TaskStatus;
import ru.adamDev.callCenter.service.exception.TaskFilterDateIsNullException;
import ru.adamDev.callCenter.service.exception.TaskIllegalNumberException;
import ru.adamDev.callCenter.service.exception.TaskIsExistException;
import ru.adamDev.callCenter.service.exception.TaskNotFoundException;
import ru.adamDev.callCenter.service.service.impl.TaskManagerServiceImpl;
import ru.adamDev.callCenter.springTest.abstractTest.AbstractSpringBootTestSuperClass;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class TaskManagerServiceImplSpringBootTest extends AbstractSpringBootTestSuperClass {

    @Autowired
    TaskManagerServiceImpl taskManagerService;
    @Autowired
    TaskRepo taskRepo;

    @BeforeEach
    public void beforeEach() {
        taskRepo.deleteAll();
    }

    @Test
    void saveTaskZeroNumber() {

        Task task = new Task(0L);
        Throwable throwable = catchThrowable(() -> taskManagerService.saveTask(task));
        assertThat(throwable).isInstanceOf(TaskIllegalNumberException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void saveTaskIsExist() throws TaskIsExistException, TaskIllegalNumberException {

        Task task = new Task(1L);
        taskManagerService.saveTask(task);
        Throwable throwable = catchThrowable(() -> taskManagerService.saveTask(task));
        assertThat(throwable).isInstanceOf(TaskIsExistException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void saveTaskDefault() throws TaskIsExistException, TaskIllegalNumberException {

        Task task = new Task(1L);
        taskManagerService.saveTask(task);
        Assert.assertEquals(taskRepo.findByNumber(1L), task);
    }

    @Test
    void finedTasksNullDate() {

        TaskFilter filter = new TaskFilter();
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void finedTasksOnlyStartDate() {

        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date());
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void finedTasksOnlyFinishDate() {

        TaskFilter filter = new TaskFilter();
        filter.setStartDate(null);
        filter.setFinishDate(new Date());
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void finedTasksDefault() throws TaskIsExistException, TaskIllegalNumberException, TaskFilterDateIsNullException {

        Task task = new Task(1);
        task.setCreateDate(new Date(0));
        taskManagerService.saveTask(task);
        Task task2 = new Task(2);
        taskManagerService.saveTask(task2);
        Task task3 = new Task(3);
        task3.setStatus(TaskStatus.COMPLETED);
        taskManagerService.saveTask(task3);

        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date(0));
        filter.setFinishDate(new Date());
        List<Task> taskList = taskManagerService.finedTasks(filter);
        Assert.assertEquals(taskList.size(), 3);


        filter.setStartDate(new Date(1));
        taskList = taskManagerService.finedTasks(filter);
        Assert.assertEquals(taskList.size(), 2);
    }

    @Test
    public void updateTaskZeroNumber() {

        UpdateTask updateTaskModel = new UpdateTask();
        Throwable throwable = catchThrowable(() -> taskManagerService.updateTask(updateTaskModel));
        assertThat(throwable).isInstanceOf(TaskIllegalNumberException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    public void updateTaskDefault() {

        UpdateTask updateTaskModel = new UpdateTask();
        updateTaskModel.setTaskId(1);
        Throwable throwable = catchThrowable(() -> taskManagerService.updateTask(updateTaskModel));
        assertThat(throwable).isInstanceOf(TaskNotFoundException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }
}