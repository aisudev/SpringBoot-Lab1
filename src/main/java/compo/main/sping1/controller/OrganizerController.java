package compo.main.sping1.controller;

import compo.main.sping1.entity.Organizer;
import compo.main.sping1.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/organizer")
public class OrganizerController {
    @Autowired
    OrganizerService organizerService;

    @GetMapping("")
    public ResponseEntity<?> getOrganizerLists(
            @RequestParam(value = "_limit", required = false)Integer perPage,
            @RequestParam(value = "_page", required = false)Integer page
    ){
        List<Organizer> resp = null;
        Integer organizerSize = organizerService.getOrganizerSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(organizerSize));

        try{
            resp = organizerService.getOrganizers(perPage, page);
            return new ResponseEntity<>(resp, responseHeader, HttpStatus.OK);

        }catch(IndexOutOfBoundsException err){
            return ResponseEntity.badRequest().body(err);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrganizer(
            @PathVariable("id") Long id
    ){
        Organizer resp = organizerService.getOrganizer(id);

        if(resp!=null){
            return ResponseEntity.ok(resp);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "event does not found");
    }
}
