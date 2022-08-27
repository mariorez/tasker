package dev.mario.tasker.core.usecase;

import dev.mario.tasker.core.domain.Bucket;
import dev.mario.tasker.core.port.out.BucketRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateBucketHandler {

    private final BucketRepository repository;

    public CreateBucketHandler(BucketRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateBucket createBucket) {
        repository.create(new Bucket()
                .setExternalId(createBucket.getExternalId())
                .setPosition(createBucket.getPosition())
                .setName(createBucket.getName()));
    }
}
