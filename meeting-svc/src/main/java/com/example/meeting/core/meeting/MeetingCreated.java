package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Event;
import lombok.Value;

import java.time.LocalDateTime;

@Value
class MeetingCreated implements Event {
     MeetingId aggregateId;
     String title;
     Person organiser;
     LocalDateTime occurredOn;
}
