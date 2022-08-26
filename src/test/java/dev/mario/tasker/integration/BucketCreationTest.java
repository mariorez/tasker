package dev.mario.tasker.integration;

import com.github.javafaker.Faker;
import com.github.jsontemplate.JsonTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class BucketCreationTest {

    @Autowired
    private MockMvc mockMvc;
    @Container
    private static final PostgreSQLContainer<?> postgresDB = new PostgreSQLContainer<>("postgres:10-bullseye")
            .withDatabaseName("testdb");

    private final Faker faker = new Faker();

    @Test
    void WHEN_CreatingBucket_GIVEN_ValidPayload_MUST_ReturnCreated() throws Exception {

        // given
        var externalId = UUID.randomUUID();
        var position = faker.number().randomDouble(5, 1, 10);
        var name = faker.pokemon().name();
        var template = "{" +
                "  id : $id," +
                "  position : @f($position)," +
                "  name : $name" +
                "}";

        var payload = new JsonTemplate(template)
                .withVar("id", externalId.toString())
                .withVar("position", position)
                .withVar("name", name)
                .prettyString();

        // then
        mockMvc.perform(post("/v1/buckets")
                        .contentType("application/json")
                        .content(payload))
                .andExpect(status().isCreated());
    }
}
