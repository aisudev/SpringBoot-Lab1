package compo.main.sping1.service;

import compo.main.sping1.entity.Organizer;

import java.util.List;

public interface OrganizerService {
    Integer getOrganizerSize();
    List<Organizer> getOrganizers(Integer pageSize, Integer page);
    Organizer getOrganizer(Long id);
}
