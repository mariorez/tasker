package dev.mario.tasker.core.usecase;

import com.github.javafaker.Faker;
import dev.mario.tasker.adapter.out.BucketRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = NONE)
class CreateBucketHandlerTest {

    @Autowired
    BucketRepositoryImpl repository;
    @Container
    private static final PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:10-bullseye")
            .withDatabaseName("testdb");

    private final Faker faker = new Faker();

    @Test
    void GIVEN_ValidData_MUST_PersistInDatabase() {

        // GIVEN
        var externalId = UUID.randomUUID();
        var position = faker.number().randomDouble(5, 1, 10);
        var name = faker.pokemon().name();
        var createBucket = new CreateBucket(externalId, position, name);

        // WHEN
        var handler = new CreateBucketHandler(repository);
        handler.handle(createBucket);

        // THEN
        var bucket = repository.findOneByExternalId(externalId).get();
        assertThat(bucket.getPosition()).isEqualTo(position);
        assertThat(bucket.getName()).isEqualTo(name);
    }
}

