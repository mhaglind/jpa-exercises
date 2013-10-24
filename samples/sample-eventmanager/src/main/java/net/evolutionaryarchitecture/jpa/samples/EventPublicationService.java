package net.evolutionaryarchitecture.jpa.samples;

import java.util.Date;

public interface EventPublicationService {

    String registerEvent(String event, Date eventTimestamp, String... agents);

    //List<Event> findEventsForItemBetween(String key, Date from, Date to);

}
