package compo.main.sping1.service;

import compo.main.sping1.entity.Event;

import java.util.List;

public interface EventService {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
}
