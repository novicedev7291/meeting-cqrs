package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Event;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
class DescriptionChanged implements Event {
    MeetingId aggregateId;
    String description;
    LocalDateTime occurredOn;
    Integer version;
}
