package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Event;
import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
class MeetingScheduled implements Event {
    MeetingId aggregateId;
    LocalDateTime scheduledOn;
    LocalDateTime occurredOn;
    Integer version;
}
