package com.sheyennevalley.repository;

import com.sheyennevalley.model.Contract;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ContractRepository extends ElasticsearchRepository<Contract, String> {

}
