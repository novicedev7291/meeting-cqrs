package com.example.meeting.core.meeting;

import lombok.Value;

@Value(staticConstructor = "of")
class CreateMeeting {
    String title;
    Organiser organiser;
}
