package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Aggregate;
import com.example.meeting.core.core.Event;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

class Meeting extends Aggregate<String, MeetingId> {
    private String title;
    private String description;
    private Organiser organiser;
    private LocalDateTime createdOn;
    private Set<Person> persons;

    private Meeting() {
    }

    public void when(Event event) {
        Match(event).of(
                Case($(instanceOf(MeetingCreated.class)), this::init),
                Case($(instanceOf(DescriptionChanged.class)), this::changeDescription)
        );
    }

    private boolean init(MeetingCreated event) {
        this.id = event.getAggregateId();
        this.title = event.getTitle();
        this.createdOn = event.getOccurredOn();
        this.organiser = event.getOrganiser();
        return true;
    }

    private boolean changeDescription(DescriptionChanged event) {
        this.description = event.getDescription();
        return true;
    }

    public static Meeting create(CreateMeeting command) {
        Meeting meeting = new Meeting();

        MeetingCreated event = new MeetingCreated(
                MeetingId.of(UUID.randomUUID().toString()),
                command.getTitle(),
                command.getOrganiser(),
                LocalDateTime.now()
        );

        meeting.applyNew(event);
        return meeting;
    }
}
