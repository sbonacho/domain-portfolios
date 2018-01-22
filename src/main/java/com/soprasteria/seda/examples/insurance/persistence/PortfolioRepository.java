package com.soprasteria.seda.examples.insurance.persistence;

import com.soprasteria.seda.examples.insurance.model.Portfolio;

import java.util.UUID;

public interface PortfolioRepository {
    Portfolio findByClientId(UUID id);
    Portfolio create(Portfolio client);
    Portfolio update(Portfolio client);
    Portfolio delete(Portfolio client);
}
