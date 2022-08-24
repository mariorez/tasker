package dev.mario.tasker.application.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bucket")
public class Bucket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NaturalId
    @Column(name = "external_id", unique = true, nullable = false, updatable = false)
    private UUID externalId;

    private double position;
    private String name;

    public Long getId() {
        return id;
    }

    public Bucket setId(Long id) {
        this.id = id;
        return this;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public Bucket setExternalId(UUID externalId) {
        this.externalId = externalId;
        return this;
    }

    public double getPosition() {
        return position;
    }

    public Bucket setPosition(double position) {
        this.position = position;
        return this;
    }

    public String getName() {
        return name;
    }

    public Bucket setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return Objects.equals(externalId, bucket.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(externalId);
    }
}
