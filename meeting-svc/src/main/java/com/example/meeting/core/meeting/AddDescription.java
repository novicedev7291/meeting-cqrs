package com.example.meeting.core.meeting;

import lombok.Value;

@Value(staticConstructor = "of")
class AddDescription {
    String description;
}
