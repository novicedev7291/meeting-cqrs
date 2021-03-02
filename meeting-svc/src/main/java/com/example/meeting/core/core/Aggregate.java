package com.example.meeting.core.core;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public abstract class Aggregate<V, T extends AggregateId<V>> {
    protected T id;
    protected List<Event> changes = new ArrayList<>();
    @Getter(AccessLevel.NONE)
    protected final AtomicInteger version = new AtomicInteger(0);

    protected void applyNew(Event event) {
        Integer currentVersion = version.get();
        Integer eventVersion = event.getVersion();
        if(eventVersion <= currentVersion)
            throw new IllegalStateException(
                    format("Event with version %d cannot be applied to aggregate with version %d", eventVersion, currentVersion)
            );

        when(event);
        changes.add(event);
    }

    protected abstract void when(Event event);
}
