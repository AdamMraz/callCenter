package ru.adamDev.callCenter.unitTest.rest;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.adamDev.callCenter.apiJson.NewTask;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.rest.TaskRestApi;
import ru.adamDev.callCenter.service.response.ResponseBody;
import ru.adamDev.callCenter.service.service.impl.TaskManagerServiceImpl;
import ru.adamDev.callCenter.unitTest.repo.RepoTest;

import java.util.Date;

class TaskRestApiTest {

    private final RepoTest repoTest = new RepoTest();
    private final TaskRestApi api =
            new TaskRestApi(new TaskManagerServiceImpl(repoTest, LoggerFactory.getLogger(TaskManagerServiceImpl.class)));

    @BeforeEach
    public void beforeEach() {
        repoTest.deleteAll();
    }

    @Test
    void add() {
        NewTask taskModel = new NewTask();
        taskModel.setNumber(1L);
        ResponseEntity response = api.add(taskModel);
        ResponseBody body = (ResponseBody) response.getBody();
        Assert.assertEquals(response, new ResponseEntity(new ResponseBody(
                body.getTimestamp(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Success"), HttpStatus.OK));
    }

    @Test
    void get() {
        Task taskModel = new Task(1);
        repoTest.save(taskModel);
        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date(0));
        filter.setFinishDate(new Date());
        ResponseEntity response = api.get(filter);
        ResponseBody body = (ResponseBody) response.getBody();
        Assert.assertEquals(response, new ResponseEntity(new ResponseBody(
                body.getTimestamp(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                repoTest.findAll()), HttpStatus.OK));
    }

    @Test
    void update() {
        Task taskModel = new Task(1);
        repoTest.save(taskModel);
        Long taskId = repoTest
                .findByNumber(1L)
                .getTaskId();
        UpdateTask updateTask = new UpdateTask();
        updateTask.setTaskId(taskId);
        updateTask.setComment("New comment");
        api.update(updateTask);
        Assert.assertEquals(repoTest
                .findById(taskId)
                .orElse(new Task())
                .getComment(), "New comment");
    }
}