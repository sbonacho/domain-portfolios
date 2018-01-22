package com.soprasteria.seda.examples.insurance.events;

import java.io.Serializable;
import java.util.UUID;

public abstract class AbstractEvent implements Serializable {

    private final String type;
    private UUID Id;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    protected AbstractEvent() {
        this.type = this.getClass().getSimpleName();
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
