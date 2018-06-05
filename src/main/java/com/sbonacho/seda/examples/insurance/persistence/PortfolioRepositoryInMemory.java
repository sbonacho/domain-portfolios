package com.sbonacho.seda.examples.insurance.persistence;

import com.sbonacho.seda.examples.insurance.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PortfolioRepositoryInMemory implements PortfolioRepository {

    private Map<UUID, Portfolio> store = new HashMap<>();
    private Map<UUID, UUID> byClients = new HashMap<>();

    @Override
    public Portfolio findByClientId(UUID id) {
        return store.get(id);
    }

    @Override
    public Portfolio create(Portfolio portfolio) {
        byClients.put(portfolio.getClientId(), portfolio.getId());
        return store.put(portfolio.getId(), portfolio);
    }

    @Override
    public Portfolio update(Portfolio portfolio) {
        return store.replace(portfolio.getId(), portfolio);
    }

    @Override
    public Portfolio delete(Portfolio portfolio){
        byClients.remove(portfolio.getClientId());
        return store.remove(portfolio.getId());
    }
}
