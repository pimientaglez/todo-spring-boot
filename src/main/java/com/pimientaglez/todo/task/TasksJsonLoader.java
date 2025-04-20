package com.pimientaglez.todo.task;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TasksJsonLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TasksJsonLoader.class);
    private final JdbcTaskRepository taskRepository;
    private final ObjectMapper objectMapper;

    public TasksJsonLoader(JdbcTaskRepository taskRepository, ObjectMapper objectMapper){
        this.taskRepository = taskRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if(taskRepository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/tasks.json")) {
                Tasks allTasks = objectMapper.readValue(inputStream, Tasks.class);
                log.info("Reading {} tasks from JSON data and saving to in-memory collection.", allTasks.tasks().size());
                taskRepository.saveAll(allTasks.tasks());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read JSON data", e);
            }
        } else {
            log.info("Not loading Tasks from JSON data because the collection contains data.");
        }
    }
}