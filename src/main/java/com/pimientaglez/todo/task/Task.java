package com.pimientaglez.todo.task;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;

public record Task (
    @Id
    Integer id,
    @NotEmpty
    String  title,
    Status status,
    @Version
    Integer version 
){

}
