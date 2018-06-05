package com.sbonacho.seda.examples.insurance.bus.kafka.listeners;

import com.sbonacho.seda.examples.insurance.bus.producer.Sender;
import com.sbonacho.seda.examples.insurance.events.*;
import com.sbonacho.seda.examples.insurance.model.Portfolio;
import com.sbonacho.seda.examples.insurance.persistence.PortfolioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientCreatedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCreatedListener.class);

    @Autowired
    private Sender<AbstractEvent> sender;

    @Autowired
    private PortfolioRepository repository;


    @KafkaListener(topics = "${connector.topics.app}")
    public void clientCreated(ClientCreated event) {
        LOGGER.info("ClientCreated Event Received -> {}", event);

        Portfolio portfolio = new Portfolio();
        portfolio.setClientId(event.getId());
        portfolio.setInterest(event.getInterest());
        portfolio.setName(event.getName());
        portfolio.setAddress(event.getAddress());
        repository.create(portfolio);

        PortfolioStored stored = new PortfolioStored();
        stored.setId(portfolio.getId());
        stored.setClientId(portfolio.getClientId());
        stored.setInterest(portfolio.getInterest());
        stored.setName(portfolio.getName());
        stored.setAddress(portfolio.getAddress());

        LOGGER.info("PortfolioStored Event Emitted -> {}", stored);
        sender.send(stored);
    }

    @KafkaListener(topics = "${connector.topics.domain}")
    public void portfolioDeleted(AbstractEvent event) {
        if (event instanceof PortfolioDeleted) {
            LOGGER.info("PortfolioDeleted Event Received -> {}", event);
            Portfolio portfolio = repository.findByClientId(event.getId());
            portfolio = repository.delete(portfolio);

            PortfolioDone done = new PortfolioDone();
            done.setId(portfolio.getId());
            done.setClientId(portfolio.getClientId());

            LOGGER.info("PortfolioDeleted Done! Event Emitted -> {}", done);
            sender.send(done);
        }
    }
}
