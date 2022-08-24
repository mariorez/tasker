package dev.mario.tasker.application.usecase;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CreateBucket {

    @JsonProperty("id")
    private final UUID externalId;
    private final double position;
    private final String name;

    public CreateBucket(UUID externalId, double position, String name) {
        this.externalId = externalId;
        this.position = position;
        this.name = name;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public double getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }
}
