package com.example.meeting.core.user;

import lombok.Value;

@Value(staticConstructor = "of")
class UserId {
    String value;
}
