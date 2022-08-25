package dev.mario.tasker.core.usecase;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateTask {

    @JsonProperty("id")
    private final UUID externalId;
    @JsonProperty("bucket_id")
    private final UUID bucketExternalId;
    private final double position;
    private final String name;

    public CreateTask(UUID externalId, UUID bucketId, double position, String name) {
        this.externalId = externalId;
        this.bucketExternalId = bucketId;
        this.position = position;
        this.name = name;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public UUID getBucketExternalId() {
        return bucketExternalId;
    }

    public double getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
