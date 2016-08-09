package com.sheyennevalley.aggregate;


import com.sheyennevalley.command.*;
import com.sheyennevalley.event.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;


import java.util.List;



@Data
@NoArgsConstructor
public class ContractAggregate extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;

    private String status;
    private String terms;
    private String offer;
    private String promisor;
    private String promisee;
    private List<String> addendums;

    @CommandHandler
    public ContractAggregate(CreateContractCommand cmd){
        this.id = cmd.getId();
        apply(new ContractCreatedEvent(cmd.getId(), cmd.getStatus(), cmd.getTerms(), cmd.getOffer(), cmd.getPromisee(), cmd.getPromisor(), cmd.getAddendums()));
    }

    @CommandHandler
    void on(ChangeOfferCommand cmd){
        apply(new OfferChangedEvent(cmd.getId(), cmd.getOffer()));
    }

    @CommandHandler
    void on(ChangeStatusCommand cmd){
        apply(new StatusChangedEvent(cmd.getId(), cmd.getStatus()));
    }

    @CommandHandler
    void on(ChangeTermsCommand cmd){
        apply(new TermsChangedEvent(cmd.getId(), cmd.getTerms()));
    }

    @CommandHandler
    void on(AddAddendumCommand cmd){
        apply(new AddendumAddedEvent(cmd.getId(), cmd.getAddendum()));
    }

    @EventSourcingHandler
    public void on(ContractCreatedEvent event){
        this.id = event.getContractId();
        this.addendums = event.getAddendums();
        this.terms = event.getTerms();
        this.status = event.getStatus();
        this.offer = event.getOffer();
        this.promisee = event.getPromisee();
        this.promisor = event.getPromisor();
    }

    @EventSourcingHandler
    public void on(AddendumAddedEvent event){
        this.id = event.getContractId();
        this.addendums.add(event.getAddendum());

    }

    @EventSourcingHandler
    public void on(OfferChangedEvent event){
        this.id = event.getContractId();
        this.offer = event.getOffer();

    }

    @EventSourcingHandler
    public void on(StatusChangedEvent event){
        this.id = event.getContractId();
        this.status = event.getStatus();
    }

    @EventSourcingHandler
    public void on(TermsChangedEvent event){
        this.id = event.getContractId();
        this.terms = event.getTerms();

    }
}
