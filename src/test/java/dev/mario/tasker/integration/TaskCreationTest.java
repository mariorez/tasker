package dev.mario.tasker.integration;

import com.github.javafaker.Faker;
import com.github.jsontemplate.JsonTemplate;
import dev.mario.tasker.adapter.out.TaskRepository;
import dev.mario.tasker.core.domain.Task;
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
public class TaskCreationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TaskRepository repository;

    private final Faker faker = new Faker();

    @Test
    void WHEN_CreatingTask_GIVEN_ValidPayload_MUST_ReturnCreated() throws Exception {

        // given
        var taskExternalId = UUID.randomUUID();
        var bucketExternalId = UUID.fromString("3731c747-ea27-42e5-a52b-1dfbfa9617db");
        var position = faker.number().randomDouble(3, 1, 10);
        var name = faker.pokemon().name();

        var template = "{" +
                "  id : $cardId," +
                "  bucket_id : $bucketId," +
                "  position : @f($position)," +
                "  name : $name" +
                "}";

        var payload = new JsonTemplate(template)
                .withVar("cardId", taskExternalId.toString())
                .withVar("bucketId", bucketExternalId.toString())
                .withVar("position", position)
                .withVar("name", name)
                .prettyString();

        // then
        mockMvc.perform(post("/v1/tasks")
                        .contentType("application/json")
                        .content(payload))
                .andExpect(status().isCreated());

        Task task = repository.finByExternalId(taskExternalId).get();
        assertThat(task.getExternalId()).isEqualTo(taskExternalId);
        assertThat(task.getPosition()).isEqualTo(position);
        assertThat(task.getName()).isEqualTo(name);
    }
}
