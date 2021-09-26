package ru.adamDev.callCenter.rest;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.adamDev.callCenter.abstractTest.AbstractSpringBootTestSuperClass;
import ru.adamDev.callCenter.apiJson.NewTask;
import ru.adamDev.callCenter.apiJson.UpdateTask;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.repo.TaskRepo;
import ru.adamDev.callCenter.apiJson.TaskFilter;
import ru.adamDev.callCenter.service.response.ResponseBody;

import java.util.Date;

class TaskRestApiSpringBootTest extends AbstractSpringBootTestSuperClass {

    @Autowired
    TaskRepo taskRepo;
    @Autowired
    TaskRestApi api;

    @BeforeEach
    public void beforeEach() {
        taskRepo.deleteAll();
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

        taskRepo.save(new Task(1L));

        TaskFilter filter = new TaskFilter();
        filter.setStartDate(new Date(0));
        filter.setFinishDate(new Date());

        ResponseEntity response = api.get(filter);
        ResponseBody body = (ResponseBody) response.getBody();
        Assert.assertEquals(response, new ResponseEntity(new ResponseBody(
                body.getTimestamp(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                taskRepo.findAll()), HttpStatus.OK));
    }

    @Test
    void update() {

        taskRepo.save(new Task(1L));
        Long taskId = taskRepo.findByNumber(1L).getTaskId();

        UpdateTask updateTaskModel = new UpdateTask();
        updateTaskModel.setTaskId(taskId);

        ResponseEntity response = api.update(updateTaskModel);
        ResponseBody body = (ResponseBody) response.getBody();
        Assert.assertEquals(response, new ResponseEntity(new ResponseBody(
                body.getTimestamp(),
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                "Success"), HttpStatus.OK));
    }
}