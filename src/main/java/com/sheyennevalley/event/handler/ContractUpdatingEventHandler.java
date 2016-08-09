package com.sheyennevalley.event.handler;

import com.sheyennevalley.event.*;
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

    @EventHandler
    void on(AddendumAddedEvent event){
        Contract contract = contractRepository.findOne(event.getContractId());
        contract.getAddendums().add(event.getAddendum());
        contractRepository.save(contract);
    }

    @EventHandler
    void on(OfferChangedEvent event){
        Contract contract = contractRepository.findOne(event.getContractId());
        contract.setOffer(event.getOffer());
        contractRepository.save(contract);
    }

    @EventHandler
    void on(StatusChangedEvent event){
        Contract contract = contractRepository.findOne(event.getContractId());
        contract.setStatus(event.getStatus());
        contractRepository.save(contract);
    }

    @EventHandler
    void on(TermsChangedEvent event){
        Contract contract = contractRepository.findOne(event.getContractId());
        contract.setTerms(event.getTerms());
        contractRepository.save(contract);
    }
}
