package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Event;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
class PersonAccepted implements Event {
    MeetingId aggregateId;
    Person person;
    LocalDateTime occurredOn;
}
