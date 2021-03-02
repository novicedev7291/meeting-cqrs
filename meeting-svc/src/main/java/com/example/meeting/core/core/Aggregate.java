package com.example.meeting.core.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Aggregate<V, T extends AggregateId<V>> {
    protected T id;
    protected List<Event> changes = new ArrayList<>();

    protected void applyNew(Event event) {
        when(event);
        changes.add(event);
    }

    protected abstract void when(Event event);
}
