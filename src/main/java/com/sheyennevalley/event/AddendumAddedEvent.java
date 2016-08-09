package com.sheyennevalley.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddendumAddedEvent {

    private String contractId;
    private String addendum;
}
