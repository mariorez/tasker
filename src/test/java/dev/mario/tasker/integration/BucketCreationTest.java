package dev.mario.tasker.integration;

import com.github.javafaker.Faker;
import com.github.jsontemplate.JsonTemplate;
import dev.mario.tasker.adapter.out.BucketRepository;
import dev.mario.tasker.application.domain.Bucket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BucketCreationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BucketRepository repository;
    private Faker faker = new Faker();

    @Test
    void GIVEN_ValidPayload_MUST_ReturnCreated() throws Exception {

        // given
        var externalId = UUID.randomUUID();
        var position = faker.number().randomDouble(5, 1, 10);
        var name = faker.pokemon().name();
        var template = "{" +
                "  id : $id," +
                "  position : $position," +
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

        Bucket bucket = repository.finByExternalId(externalId).get();
        assertThat(bucket.getExternalId()).isEqualTo(externalId);
        assertThat(bucket.getPosition()).isEqualTo(position);
        assertThat(bucket.getName()).isEqualTo(name);
    }
}
