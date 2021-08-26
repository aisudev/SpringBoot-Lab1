package compo.main.sping1.dao;

import compo.main.sping1.entity.Organizer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init(){
        organizerList = new ArrayList<>();

        organizerList.add(Organizer.builder()
                .id(1L)
                .name("Tesla")
                .address("San Francisco")
                .build());

        organizerList.add(Organizer.builder()
                .id(2L)
                .name("Boston Dynamic")
                .address("Boston")
                .build());

        organizerList.add(Organizer.builder()
                .id(3L)
                .name("Google")
                .address("San Francisco")
                .build());

        organizerList.add(Organizer.builder()
                .id(4L)
                .name("Microsoft")
                .address("San Francisco")
                .build());

        organizerList.add(Organizer.builder()
                .id(5L)
                .name("Facebook")
                .address("San Francisco")
                .build());

        organizerList.add(Organizer.builder()
                .id(6L)
                .name("Apple")
                .address("San Francisco")
                .build());
    }

    @Override
    public Integer getOrganizerSize(){
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page) {
        pageSize = pageSize == null?organizerList.size():pageSize;
        page = page == null?1:page;

        int firstIndex = (page - 1) * pageSize;
        return organizerList.subList(firstIndex, firstIndex+pageSize);
    }

    @Override
    public Organizer getOrganizer(Long id) {
        return organizerList.stream()
                .filter(e->e.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
