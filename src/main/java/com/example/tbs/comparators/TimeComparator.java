package com.example.tbs.comparators;

import com.example.tbs.models.Event;

import java.util.Comparator;

public class TimeComparator implements Comparator<Event> {
    @Override
    public int compare(Event t, Event t1) {
        if (t.getFrom() == t1.getFrom()) {
            return 0;
        } else if (t.getFrom().before(t1.getFrom())) {
            return -1;
        } else {
            return 1;
        }
    }
}
