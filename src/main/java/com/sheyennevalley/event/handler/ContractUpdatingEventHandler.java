package com.sheyennevalley.event.handler;

import com.sheyennevalley.event.ContractCreatedEvent;
import com.sheyennevalley.model.Contract;
import com.sheyennevalley.repository.ContractRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContractUpdatingEventHandler {

    @Autowired
    private ContractRepository contractRepository;

    @EventHandler
    void on(ContractCreatedEvent event) {
        Contract contract = Contract.builder()
                .id(event.getContractId())
                .offer(event.getOffer())
                .terms(event.getTerms())
                .promisee(event.getPromisee())
                .promisor(event.getPromisor())
                .addendums(event.getAddendums())
                .build();
        contractRepository.save(contract);
    }
}
