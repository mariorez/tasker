package dev.mario.tasker.adapter.out;

import dev.mario.tasker.core.domain.Bucket;
import dev.mario.tasker.core.port.out.BucketRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BucketRepositoryImpl extends BucketRepository, JpaRepository<Bucket, Long> {

    default void create(Bucket bucket) {
        save(bucket);
    }

    @Query("SELECT b FROM Bucket b WHERE b.externalId=?1")
    Optional<Bucket> findOneByExternalId(UUID externalId);
}
