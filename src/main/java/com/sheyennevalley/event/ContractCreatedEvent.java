package com.sheyennevalley.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ContractCreatedEvent {

    private String contractId;
    private String status;
    private String terms;
    private String offer;
    private String promisor;
    private String promisee;
    private List<String> addendums;
}
