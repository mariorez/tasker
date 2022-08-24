package dev.mario.tasker.core.usecase;

import dev.mario.tasker.adapter.out.BucketRepository;
import dev.mario.tasker.core.domain.Bucket;
import org.springframework.stereotype.Service;

@Service
public class CreateBucketHandler {

    private final BucketRepository repository;

    public CreateBucketHandler(BucketRepository repository) {
        this.repository = repository;
    }

    public void handle(CreateBucket createBucket) {
        repository.save(new Bucket()
                .setExternalId(createBucket.getExternalId())
                .setPosition(createBucket.getPosition())
                .setName(createBucket.getName()));
    }
}
