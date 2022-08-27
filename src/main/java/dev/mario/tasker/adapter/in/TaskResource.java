package dev.mario.tasker.adapter.in;

import dev.mario.tasker.core.usecase.CreateTask;
import dev.mario.tasker.core.usecase.CreateTaskHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/tasks")
public class TaskResource {

    @Autowired
    private CreateTaskHandler createTaskHandler;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateTask createTask) {
        createTaskHandler.handle(createTask);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
