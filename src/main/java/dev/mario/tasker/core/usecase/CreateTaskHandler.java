package dev.mario.tasker.core.usecase;

import dev.mario.tasker.core.domain.Task;
import dev.mario.tasker.core.port.out.BucketRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateTaskHandler {

    private final BucketRepository repository;

    public CreateTaskHandler(BucketRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateTask createTask) {

        var bucket = repository.findOneByExternalId(createTask.getBucketExternalId()).orElseThrow();

        repository.create(bucket.addTask(new Task()
                .setExternalId(createTask.getExternalId())
                .setPosition(createTask.getPosition())
                .setName(createTask.getName())));
    }
}
