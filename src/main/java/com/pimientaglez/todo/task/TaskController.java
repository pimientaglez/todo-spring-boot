package com.pimientaglez.todo.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
class TaskController {

    private final JdbcTaskRepository TaskRepository;

    TaskController(JdbcTaskRepository TaskRepository) {
        this.TaskRepository = TaskRepository;
    }

    @GetMapping
    List<Task> findAll() {
        return TaskRepository.findAll();
    }

    @GetMapping("/{id}")
    Task findById(@PathVariable Integer id) {
        Optional<Task> Task = TaskRepository.findById(id);
        if(Task.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found.");
        }
        return Task.get();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Task create(@Valid @RequestBody Task task) {
        Task Task = TaskRepository.create(task);
        return Task;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    void update(@Valid @RequestBody Task Task, @PathVariable Integer id) {
        TaskRepository.update(Task,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        TaskRepository.deleteById(id);
    }

    @GetMapping("/by-status")
    List<Task> findByStatus(@RequestParam Status status) {
        return TaskRepository.findAllByStatus(status);
    }
}