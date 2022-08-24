package dev.mario.tasker.adapter.out;

import dev.mario.tasker.core.domain.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BucketRepository extends JpaRepository<Bucket, Long> {

    @Query("SELECT b FROM Bucket b WHERE b.externalId=?1")
    Optional<Bucket> finByExternalId(UUID externalId);
}
