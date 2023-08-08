package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Gab;
import app.hesias.gabbler.Repository.GabRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class GabServiceImpl implements GabService{
    private final GabRepo gabRepo;

    @Override
    public List<Gab> getAllGabs() {
        return gabRepo.findAll();
    }

    @Override
    public Gab getGabById(int id) {
        return gabRepo.findById(id).orElse(null);
    }

    @Override
    public Gab createGab(Gab gab) {
        gab.setCreatedAt(LocalDateTime.now());
        gabRepo.save(gab);
        return gab;
    }

    @Override
    public Gab updateGab(int id, Gab gab) {
        Gab gabToUpdate = gabRepo.findById(id).orElse(null);
        if (gabToUpdate != null) {
            gabToUpdate.setContent(gab.getContent());
            gabToUpdate.setCreatedAt(gab.getCreatedAt());
            gabToUpdate.setMediaUrl(gab.getMediaUrl());
            gabRepo.save(gabToUpdate);
        }
        return gabToUpdate;
    }

    @Override
    public Gab deleteGab(int id) {
        Gab gabToDelete = gabRepo.findById(id).orElse(null);
        if (gabToDelete != null) {
            gabRepo.delete(gabToDelete);
        }
        return gabToDelete;
    }
}
