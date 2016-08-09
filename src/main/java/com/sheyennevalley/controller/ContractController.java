package com.sheyennevalley.controller;

import com.sheyennevalley.command.*;
import com.sheyennevalley.model.Contract;
import com.sheyennevalley.repository.ContractRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ContractController {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CommandGateway commandGateway;

    @RequestMapping(value = "/api/contracts", method = POST)
    @ResponseStatus(value = NO_CONTENT)
    public void createContract(@RequestBody CreateContractCommand command) {
        command.setId(UUID.randomUUID().toString());
        commandGateway.send(command);
    }

    @RequestMapping(value = "/api/contracts/{id}/offer", method = POST)
    @ResponseStatus(value = NO_CONTENT)
    private void updateOffer(@PathVariable String id, @RequestBody String offer){
        ChangeOfferCommand command = new ChangeOfferCommand(id, offer);
        commandGateway.send(command);
    }

    @RequestMapping(value = "/api/contracts/{id}/terms", method = POST)
    @ResponseStatus(value = NO_CONTENT)
    private void updateTerms(@PathVariable String id, @RequestBody String terms){
        ChangeTermsCommand command = new ChangeTermsCommand(id, terms);
        commandGateway.send(command);
    }

    @RequestMapping(value = "/api/contracts/{id}/status", method = POST)
    @ResponseStatus(value = NO_CONTENT)
    private void updateStatus(@PathVariable String id, @RequestBody String status){
        ChangeStatusCommand command = new ChangeStatusCommand(id, status);
        commandGateway.send(command);
    }

    @RequestMapping(value = "/api/contracts/{id}/addendum", method = POST)
    @ResponseStatus(value = NO_CONTENT)
    private void addAddendum(@PathVariable String id, @RequestBody String addendum){
        AddAddendumCommand command = new AddAddendumCommand(id, addendum);
        commandGateway.send(command);
    }

    @RequestMapping(value = "/api/contracts/{id}", method = GET)
    @ResponseBody
    private Contract findOnd(@PathVariable String id){
        return contractRepository.findOne(id);
    }

}



