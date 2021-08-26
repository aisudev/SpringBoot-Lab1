package compo.main.sping1.controller;

import compo.main.sping1.entity.Event;
import compo.main.sping1.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    @GetMapping("")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false)Integer perPage,
            @RequestParam(value = "_page", required = false)Integer page
    ){
        List<Event> resp = null;
        Integer eventSize = eventService.getEventSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(eventSize));

        try{
            resp = eventService.getEvents(perPage, page);
            return new ResponseEntity<>(resp, responseHeader, HttpStatus.OK);

        }catch(IndexOutOfBoundsException err){
            return ResponseEntity.badRequest().body(err);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEvent(
            @PathVariable("id") Long id
    ){
        Event resp = eventService.getEvent(id);

        if(resp!=null){
            return ResponseEntity.ok(resp);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event does not found");
    }
}
