package com.example.meeting.core.meeting;

import lombok.Value;

import java.time.LocalDateTime;

@Value
class MeetingCreated {
     String aggregateId;
     String title;
     Organiser organiser;
     LocalDateTime createdOn;
}
