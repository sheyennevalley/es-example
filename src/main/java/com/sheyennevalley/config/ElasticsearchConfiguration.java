package com.sheyennevalley.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.sheyennevalley.repository")
public class ElasticsearchConfiguration {
}
