package com.sheyennevalley.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Document(indexName = "contracts", type = "entry")
public class Contract {

    public Contract(){

    }

    @Id
    private String id;
    private String status;
    private String terms;
    private String offer;
    private String promisor;
    private String promisee;
    private List<String> addendums;


}
