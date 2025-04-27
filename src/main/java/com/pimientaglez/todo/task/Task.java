    package com.pimientaglez.todo.task;


    import java.time.LocalDateTime;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.annotation.Version;

    import jakarta.validation.constraints.NotEmpty;
    import jakarta.validation.constraints.NotNull;

    public record Task (
        @Id
        Integer id,
        @NotEmpty
        String  title,
        @NotNull
        Status status,
        @Version
        Integer version,
        @NotNull
        LocalDateTime createDate,
        LocalDateTime completeDate,
        @NotNull
        Priority priority
    ){

    }
