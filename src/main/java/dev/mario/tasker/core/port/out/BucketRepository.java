package dev.mario.tasker.core.port.out;

import dev.mario.tasker.core.domain.Bucket;

import java.util.Optional;
import java.util.UUID;

public interface BucketRepository {

    void create(Bucket bucket);

    Optional<Bucket> findOneByExternalId(UUID externalId);
}
