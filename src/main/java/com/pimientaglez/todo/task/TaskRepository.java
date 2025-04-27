package com.pimientaglez.todo.task;

import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

public interface TaskRepository extends ListCrudRepository<Task, Integer> {

        List<Task> findAllByStatus(Status status);
        List<Task> findAllByPriority(Priority priority);
    
}
