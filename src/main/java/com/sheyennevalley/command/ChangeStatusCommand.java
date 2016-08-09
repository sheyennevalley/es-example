package com.sheyennevalley.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class ChangeStatusCommand {

    @TargetAggregateIdentifier
    private String id;
    private String status;
}
