package com.example.meeting.core.meeting;

import com.example.meeting.core.core.AggregateId;
import lombok.Value;

@Value(staticConstructor = "of")
class MeetingId implements AggregateId<String> {
    String value;
}
