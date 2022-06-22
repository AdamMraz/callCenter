package ru.adamDev.callCenter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.adamDev.callCenter.model.Task;
import ru.adamDev.callCenter.service.enums.TaskStatus;

import java.util.Date;
import java.util.List;

/**
 * The task repository
 */
@Repository
public interface TaskRepo extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

    /**
     * Search for a task by number
     *
     * @param number The number of the desired task
     * @return The founded task
     */
    @Query("SELECT t FROM Task t WHERE t.number = :number")
    Task findByNumber(@Param("number") Long number);

    /**
     * Search for a tasks by filters
     *
     * @param startDate Start date for the search
     * @param finishDate The end date for the search
     * @param number The number of the task to search
     * @param status Status of the search task
     * @return List of found tasks
     */
    @Query("SELECT t FROM Task t WHERE (t.createDate >= :startDate) " +
            "AND (t.createDate <= :finishDate) " +
            "AND (t.number = :number OR :number IS NULL) " +
            "AND (t.status = :status OR :status IS NULL)")
    List<Task> findByFilter(@Param("startDate") Date startDate,
                            @Param("finishDate") Date finishDate,
                            @Param("number") Long number,
                            @Param("status") TaskStatus status);
}
