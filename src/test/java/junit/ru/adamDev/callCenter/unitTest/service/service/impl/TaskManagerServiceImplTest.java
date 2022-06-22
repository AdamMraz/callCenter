package ru.adamDev.callCenter.unitTest.service.service.impl;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.LoggerFactory;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.exception.TaskFilterDateIsNullException;
import ru.adamDev.callCenter.service.exception.TaskIllegalNumberException;
import ru.adamDev.callCenter.service.exception.TaskIsExistException;
import ru.adamDev.callCenter.service.exception.TaskNotFoundException;
import ru.adamDev.callCenter.service.service.TaskManagerService;
import ru.adamDev.callCenter.service.service.impl.TaskManagerServiceImpl;
import ru.adamDev.callCenter.unitTest.repo.RepoTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TaskManagerServiceImplTest {

    private final RepoTest repoTest = new RepoTest();
    private final TaskManagerService taskManagerService
            = new TaskManagerServiceImpl(this.repoTest, LoggerFactory.getLogger(TaskManagerServiceImpl.class));

    @BeforeEach
    void clearDb() {
        repoTest.deleteAll();
    }

    @Test
    void saveTaskZeroNumber() {
        Task task = new Task(0L);
        Throwable throwable = catchThrowable(() -> taskManagerService.saveTask(task));
        assertThat(throwable).isInstanceOf(TaskIllegalNumberException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    void saveTaskIsExist() throws TaskIsExistException, TaskIllegalNumberException {
        Task task = new Task(1L);
        taskManagerService.saveTask(task);
        Throwable throwable = catchThrowable(() -> taskManagerService.saveTask(task));
        assertThat(throwable).isInstanceOf(TaskIsExistException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    void saveTaskDefault() throws TaskIsExistException, TaskIllegalNumberException {
        Task task = new Task(1L);
        taskManagerService.saveTask(task);
        Assert.assertEquals(repoTest.findByNumber(1L), task);
    }

    @Test
    void finedTasksNullDate() {
        TaskFilter filter = new TaskFilter();
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    void finedTasksOnlyStartDate() {
        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date());
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    void finedTasksOnlyFinishDate() {
        TaskFilter filter = new TaskFilter();
        filter.setStartDate(null);
        filter.setFinishDate(new Date());
        Throwable throwable = catchThrowable(() -> taskManagerService.finedTasks(filter));
        assertThat(throwable).isInstanceOf(TaskFilterDateIsNullException.class);
        assertThat(throwable.getMessage()).isNotBlank();
    }

    @Test
    void finedTasksDefault() throws TaskIsExistException, TaskIllegalNumberException, TaskFilterDateIsNullException {
        Task task = new Task(1);
        task.setCreateDate(new Date(0));
        taskManagerService.saveTask(task);
        taskManagerService.saveTask(new Task(2));
        taskManagerService.saveTask(new Task(3));
        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date(0));
        filter.setFinishDate(new Date(Long.MAX_VALUE));
        List<Task> taskListThree = taskManagerService.finedTasks(filter);
        Assert.assertEquals(taskListThree.size(), 3);
        filter.setStartDate(new Date(1));
        filter.setFinishDate(new Date(Long.MAX_VALUE));
        List<Task> taskListTwo = taskManagerService.finedTasks(filter);
        Assert.assertEquals(taskListTwo.size(), 2);
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