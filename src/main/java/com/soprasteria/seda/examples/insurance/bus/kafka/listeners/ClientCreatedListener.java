package com.soprasteria.seda.examples.insurance.bus.kafka.listeners;

import com.soprasteria.seda.examples.insurance.bus.producer.Sender;
import com.soprasteria.seda.examples.insurance.events.ClientCreated;
import com.soprasteria.seda.examples.insurance.events.PortfolioStored;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ClientCreatedListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientCreatedListener.class);

    @Autowired
    private Sender<PortfolioStored> sender;


    @KafkaListener(topics = "${connector.topic.in}")
    public void clientCreated(ClientCreated event) {
        LOGGER.info("ClientCreated Event Received -> {}", event);

        PortfolioStored stored = new PortfolioStored();
        stored.setClientId(event.getId());
        stored.setInterest(event.getInterest());
        stored.setName(event.getName());
        stored.setAddress(event.getAddress());

        LOGGER.info("PortfolioStored Event Emitted -> {}", stored);
        sender.send(stored);
    }
}
