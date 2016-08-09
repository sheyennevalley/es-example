package com.sheyennevalley.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatusChangedEvent {

    private String contractId;
    private String status;
}
