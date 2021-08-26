package compo.main.sping1.controller;

import compo.main.sping1.Event;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        eventList.add(Event.builder().id(123L).category("animal").title("animal welfare")
                .description("find your new feline at this event.").location("Meow Town")
                .date("January 28, 2022").time("12:00").petAllowed(true).organizer("Kat Laydee")
                .build());

        eventList.add(Event.builder().id(456L).category("food").title("Community Garden")
                .description("join us as we tend to the community edible plants.")
                .location("Flora City").date("March 14, 2022").time("12:00").petAllowed(false)
                .organizer("Fern Pollin").build());

        eventList.add(Event.builder().id(789L).category("sustainability").title("Beach Cleanup")
                .description("Help pick up trash along the shore.").location("Playa Del Carmen")
                .date("July 22, 2022").time("11:00").petAllowed(false).organizer("Carey Wales")
                .build());

        eventList.add(Event.builder().id(1001L).category("Dog Adoption Day").title("animal welfare")
                .description("Find your new canine friend at this event.").location("Woof Town")
                .date("August 28, 2022").time("12:00").petAllowed(true).organizer("Dawg Dahd").build());

        eventList.add(Event.builder().id(1002L).category("food").title("Canned Food Drive")
                .description("Bring your canned food to donate to those in need.").location("Tin City")
                .date("September 14, 2022").time("3:00").petAllowed(true).organizer("Kahn Opiner")
                .build());

        eventList.add(Event.builder().id(1002L).category("sustainability").title("Highway Cleanup")
                .description("Help pick up trash along the highway.").location("Highway 50")
                .date("July 22, 2022").time("11:00").petAllowed(false).organizer("Brody Kill").build());
    }

    @GetMapping("")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false)Integer perPage,
            @RequestParam(value = "_page", required = false)Integer page
    ){
        perPage = perPage == null?eventList.size():perPage;
        page = page == null?1:page;

        Integer firstIndex = (page-1)*perPage;
        List<Event> resp = new ArrayList<>();

        try{
            for(int i = 0;i < firstIndex + perPage;i++){
                resp.add(eventList.get(i));
            }
            return ResponseEntity.ok(resp);

        }catch(IndexOutOfBoundsException err){
            return ResponseEntity.badRequest().body(err);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(
            @PathVariable("id") Long id
    ){
        Event resp = null;
        for(Event e:eventList){
            if(e.getId().equals(id)){
                resp = e;
            }
        }

        if(resp!=null){
            return ResponseEntity.ok(resp);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event does not found");
    }
}
