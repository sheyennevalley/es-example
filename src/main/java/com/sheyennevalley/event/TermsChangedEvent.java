package com.sheyennevalley.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TermsChangedEvent {

    private String contractId;
    private String terms;
}
