package com.example.meeting.core.meeting;

import com.example.meeting.core.core.Aggregate;
import com.example.meeting.core.core.Event;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static io.vavr.API.$;
import static io.vavr.API.Case;
import static io.vavr.API.Match;
import static io.vavr.Predicates.instanceOf;

@Getter
class Meeting extends Aggregate<String, MeetingId> {
    private String title;
    private String description;
    private Person organiser;
    private LocalDateTime createdOn;
    private LocalDateTime scheduledAt;
    @Getter(AccessLevel.NONE)
    private final Set<Person> persons = new HashSet<>();

    private Meeting() {
    }

    public void when(Event event) {
        Match(event).of(
                Case($(instanceOf(MeetingCreated.class)), this::init),
                Case($(instanceOf(DescriptionChanged.class)), this::changeDescription),
                Case($(instanceOf(MeetingScheduled.class)), this::scheduleMeeting),
                Case($(instanceOf(PersonAccepted.class)), this::addPerson)
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

    private boolean scheduleMeeting(MeetingScheduled event) {
        this.scheduledAt = event.getScheduledOn();
        return true;
    }

    private boolean addPerson(PersonAccepted event) {
        persons.add(event.getPerson());
        return true;
    }

    public Set<Person> getPersons() {
        return Collections.unmodifiableSet(persons);
    }

    public Integer getVersion() {
        return version.get();
    }

    public void accept(Person person) {
        applyNew(PersonAccepted.of(id, person, LocalDateTime.now(), version.incrementAndGet()));
    }

    public void changeDescription(AddDescription command) {
       applyNew(DescriptionChanged.of(id, command.getDescription(), LocalDateTime.now(), version.incrementAndGet()));
    }

    public void schedule(ScheduleMeeting command) {
        applyNew(MeetingScheduled.of(id, command.scheduleOn, LocalDateTime.now(), version.incrementAndGet()));
    }

    public static Meeting create(CreateMeeting command) {
        Meeting meeting = new Meeting();

        MeetingCreated event = new MeetingCreated(
                MeetingId.of(UUID.randomUUID().toString()),
                command.getTitle(),
                command.getOrganiser(),
                LocalDateTime.now(),
                meeting.version.incrementAndGet()
        );

        meeting.applyNew(event);
        return meeting;
    }
}
