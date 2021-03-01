package com.example.meeting.core.core;

import java.time.LocalDateTime;

public interface Event {
    AggregateId<?> getAggregateId();
    LocalDateTime getOccurredOn();
}
