package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Name;
import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class Person {
    private PersonId id;
    private Name name;
    private String email;
}
