package com.example.meeting.core.meeting;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.Set;

@Value(staticConstructor = "of")
class Meeting {
    MeetingId id;
    String title;
    String description;
    LocalDateTime createdOn;
    Set<Person> persons;
}
