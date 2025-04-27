package com.pimientaglez.todo.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/* import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Optional; */
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

//import jakarta.annotation.PostConstruct;

@Repository
public class JdbcTaskRepository implements TaskRepository {

    private final JdbcClient jdbcClient;

    public JdbcTaskRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Task> findAll() {
        return jdbcClient.sql("select * from task ORDER BY id ASC")
                .query(Task.class)
                .list();
    }

    public Optional<Task> findById(Integer id) {
        return jdbcClient.sql("SELECT * version FROM Task WHERE id = :id" )
                .param("id", id)
                .query(Task.class)
                .optional();
    }

    public Task create(Task task) {
        Integer newId = jdbcClient.sql("INSERT INTO Task(title,status,create_date,priority) values(?,?,?,?) RETURNING id")
                .params(List.of(task.title(),task.status().toString(), task.createDate(),task.priority().toString()))
                .query(Integer.class)
                .single();

        Assert.notNull(newId, "Failed to retrieve generated ID for Task: " + task.title());
        return new Task(
            newId,
            task.title(),
            task.status(),
            task.version(),
            task.createDate(),
            task.completeDate(),
            task.priority()
        );
    }

    public Task update(Task task, Integer id) {
        int rowsAffected = jdbcClient.sql("update task set title = ?, status = ?, complete_date = ?, priority = ? where id = ?")
                .params(Arrays.asList(task.title(),task.status().toString(), task.completeDate(),task.priority().toString(), id))
                .update();

        Assert.state(rowsAffected == 1, "Failed to update task " + task.title());
        return task;
    }

    public void delete(Task task) {
        var updated = jdbcClient.sql("delete from task where id = :id")
                .param("id", task.id())
                .update();

        Assert.state(updated == 1, "Failed to delete task " + task.id());
    }
    public void deleteById(Integer id) {
        var updated = jdbcClient.sql("delete from task where id = :id")
                .param("id", id)
                .update();

        Assert.state(updated == 1, "Failed to delete task " + id);
    }

    public long count() {
        return jdbcClient.sql("select * from task").query().listOfRows().size();
    }

    @Override
    public <S extends Task> List<S> saveAll(Iterable<S> tasks) {
        List<S> savedTasks = new ArrayList<>();
        for (S task : tasks) {
            create(task);
            savedTasks.add(task);
        }
        return savedTasks;
    }

    public List<Task> findAllByStatus(Status status) {
        return jdbcClient.sql("select * from task where status = :status")
                .param("status", status.toString())
                .query(Task.class)
                .list();
    }
    public List<Task> findAllByPriority(Priority priority) {
        return jdbcClient.sql("select * from task where priority = :priority")
                .param("priority", priority.toString())
                .query(Task.class)
                .list();
    }

}