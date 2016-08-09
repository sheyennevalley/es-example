package com.sheyennevalley.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OfferChangedEvent {

    private String contractId;
    private String offer;

}
