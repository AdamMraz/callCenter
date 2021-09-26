package ru.adamDev.callCenter.repo;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.enums.TaskStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class RepoTest implements TaskRepo {

    private static List<Task> taskList = new ArrayList<>();
    private static Long iterator = 1L;

    @Override
    public Task findByNumber(Long number) {
        return RepoTest.taskList
                .stream()
                .filter(t -> t.getNumber() == number)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Task> findByFilter(Date startDate, Date finishDate, Long number, TaskStatus status) {
        return RepoTest.taskList
                .stream()
                .filter(t -> ((t.getCreateDate().after(startDate) || t.getCreateDate().equals(startDate)) &&
                        (t.getCreateDate().before(finishDate) || t.getCreateDate().equals(finishDate)) &&
                        (number == null || t.getNumber() == number) &&
                        (status == null || t.getStatus().equals(status))))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> findAll() {
        return RepoTest.taskList;
    }

    @Override
    public List<Task> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Task> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Task> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return RepoTest.taskList.size();
    }

    @Override
    public void deleteById(Long aLong) {
        RepoTest.taskList.removeIf(task -> task.getTaskId() == aLong);
    }

    @Override
    public void delete(Task task) {
        RepoTest.taskList.remove(task);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Task> iterable) {

    }

    @Override
    public void deleteAll() {
        RepoTest.taskList.clear();
    }

    @Override
    public <S extends Task> S save(S s) {
        Assert.notNull(s, "Entity must not be null.");
        if (!RepoTest.taskList.contains(s)) {
            s.setTaskId(RepoTest.iterator++);
            RepoTest.taskList.add(s);
        }
        return s;
    }

    @Override
    public <S extends Task> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Task> findById(Long aLong) {
        return RepoTest.taskList
                .stream()
                .filter(t -> t.getTaskId() == aLong)
                .findFirst();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Task> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Task> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Task> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Task getOne(Long aLong) {
        return null;
    }

    @Override
    public Task getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Task> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Task> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Task> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Task> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Task> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public Optional<Task> findOne(Specification<Task> specification) {
        return Optional.empty();
    }

    @Override
    public List<Task> findAll(Specification<Task> specification) {
        return null;
    }

    @Override
    public Page<Task> findAll(Specification<Task> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<Task> findAll(Specification<Task> specification, Sort sort) {
        return null;
    }

    @Override
    public long count(Specification<Task> specification) {
        return 0;
    }
}