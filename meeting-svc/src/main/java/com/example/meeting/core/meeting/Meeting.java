package com.example.meeting.core.meeting;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class Meeting {
    private MeetingId id;
    private String title;
    private String description;
    private Organiser organiser;
    private LocalDateTime createdOn;
    private Set<Person> persons;
    private List<Object> unflushedEvents = new ArrayList<Object>();

    private Meeting() {}

    protected void applyNew(MeetingCreated event) {
       unflushedEvents.add(event);
       apply(event);
    }

    public void apply(MeetingCreated event) {
        this.id = MeetingId.of(event.getAggregateId());
        this.title = event.getTitle();
        this.createdOn = event.getCreatedOn();
        this.organiser = event.getOrganiser();
    }

    public static Meeting create(CreateMeeting command) {
        Meeting meeting = new Meeting();

        MeetingCreated event = new MeetingCreated(
                UUID.randomUUID().toString(),
                command.getTitle(),
                command.getOrganiser(),
                LocalDateTime.now()
        );

        meeting.applyNew(event);
        return meeting;
    }
}
