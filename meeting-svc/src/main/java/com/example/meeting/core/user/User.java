package com.example.meeting.core.user;

import com.example.meeting.core.common.Name;
import lombok.Value;

@Value(staticConstructor = "of")
public class User {
    UserId id;
    String email;
    Name name;
}
