package com.sheyennevalley.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class AddAddendumCommand {

    @TargetAggregateIdentifier
    private String id;
    private String addendum;
}
