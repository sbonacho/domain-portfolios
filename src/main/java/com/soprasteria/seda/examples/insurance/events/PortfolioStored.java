package com.soprasteria.seda.examples.insurance.events;

import java.util.UUID;

public class PortfolioStored extends AbstractEvent {

    public PortfolioStored() {
        this.portfolioId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public UUID getPortfolioId() {
        return portfolioId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    private UUID clientId;
    private final UUID portfolioId;
    private String name;
    private String address;
    private String interest;

}
