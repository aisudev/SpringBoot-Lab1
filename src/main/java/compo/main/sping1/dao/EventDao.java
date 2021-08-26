package compo.main.sping1.dao;

import compo.main.sping1.entity.Event;

import java.util.List;

public interface EventDao {
    Integer getEventSize();
    List<Event> getEvents(Integer pageSize, Integer page);
    Event getEvent(Long id);
}
