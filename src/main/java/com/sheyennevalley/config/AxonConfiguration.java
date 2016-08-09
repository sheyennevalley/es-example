package com.sheyennevalley.config;

import com.mongodb.Mongo;
import com.sheyennevalley.aggregate.ContractAggregate;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.annotation.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventhandling.annotation.AnnotationEventListenerBeanPostProcessor;
import org.axonframework.eventsourcing.EventSourcingRepository;

import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AnnotationDriven
@Configuration
public class AxonConfiguration {

    @Autowired
    private Mongo mongo;

    @Bean
    public AnnotationEventListenerBeanPostProcessor annotationEventListenerBeanPostProcessor() {
        AnnotationEventListenerBeanPostProcessor processor = new AnnotationEventListenerBeanPostProcessor();
        processor.setEventBus(eventBus());
        return processor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        AnnotationCommandHandlerBeanPostProcessor processor = new AnnotationCommandHandlerBeanPostProcessor();
        processor.setCommandBus(commandBus());
        return processor;
    }

    @Bean
    public CommandBus commandBus(){
        return new SimpleCommandBus();
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public CommandGatewayFactoryBean<CommandGateway> commandGatewayFactoryBean() {
        CommandGatewayFactoryBean<CommandGateway> factory = new CommandGatewayFactoryBean<>();
        factory.setCommandBus(commandBus());
        return factory;
    }
    @Bean
    public MongoTemplate axonMongoTemplate(){
        MongoTemplate template = new DefaultMongoTemplate(mongo);
        return template;
    }

    @Bean
    public EventStore eventStore(){
        return new MongoEventStore(axonMongoTemplate());
    }

    @Bean
    public EventSourcingRepository<ContractAggregate> contractEventRepository() {
        EventSourcingRepository<ContractAggregate> repository = new EventSourcingRepository<>(ContractAggregate.class, eventStore());
        repository.setEventBus(eventBus());
        return repository;
    }

    @Bean
    public AggregateAnnotationCommandHandler<ContractAggregate> contractCommandHandler() {
        return AggregateAnnotationCommandHandler.subscribe(ContractAggregate.class, contractEventRepository(), commandBus());
    }
}
