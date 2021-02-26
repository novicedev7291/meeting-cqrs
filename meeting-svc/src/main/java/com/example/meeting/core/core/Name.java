package com.example.meeting.core.core;

import lombok.Value;

@Value(staticConstructor = "of")
public class Name {
    String firstName;
    String lastName;

    public String value() {
        return firstName + " " + lastName;
    }
}
