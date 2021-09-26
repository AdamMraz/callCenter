package ru.adamDev.callCenter.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.adamDev.callCenter.service.enums.TaskStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long taskId;

    @NotNull
    private long number;

    @Column(name = "create_date")
    @NotNull
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @NotNull
    private TaskStatus status;

    private String comment;

    public Task(long number) {
        this.number = number;
        this.createDate = new Date();
        this.status = TaskStatus.NOT_COMPLETED;
        this.comment = "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return taskId == task.taskId &&
                number == task.number &&
                Objects.equals((createDate != null ? createDate.getTime() : null), (task.createDate != null ? task.createDate.getTime() : null)) &&
                Objects.equals((updateDate != null ? updateDate.getTime() : null), (task.updateDate != null ? task.updateDate.getTime() : null)) &&
                status == task.status &&
                Objects.equals(comment, task.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, number, createDate, updateDate, status, comment);
    }
}
