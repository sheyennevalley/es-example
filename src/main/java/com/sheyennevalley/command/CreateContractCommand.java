package com.sheyennevalley.command;

import lombok.Data;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

@Data
public class CreateContractCommand {

    @TargetAggregateIdentifier
    private String id;

    private String status;
    private String terms;
    private String offer;
    private String promisor;
    private String promisee;
    private List<String> addendums;
}
