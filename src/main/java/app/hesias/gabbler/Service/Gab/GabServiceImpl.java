package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.GabResult;
import app.hesias.gabbler.Model.Result.UserResult;
import app.hesias.gabbler.Repository.GabRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class GabServiceImpl implements GabService{
    private final GabRepo gabRepo;

    @Override
    public List<Gab> getAllGabs() {
        return gabRepo.findAll();
    }

    @Override
    public GabResult getGabById(int id) {
        try {
            Gab gab = gabRepo.findById(id).orElse(null);
            return gab != null ? new GabResult(gab, RequestStatus.OK) : new GabResult(null, RequestStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new GabResult(null, RequestStatus.BAD_REQUEST);
        }
    }

    @Override
    public GabResult createGab(Gab gab) {
        gab.setCreatedAt(LocalDateTime.now());
        try {
            gabRepo.save(gab);
            return new GabResult(gab, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new GabResult(null, RequestStatus.BAD_REQUEST);
        }
    }

    @Override
    public GabResult updateGab(int id, Gab gab) {
        try {
            Gab gabToUpdate = gabRepo.findById(id).orElse(null);
            if (gabToUpdate != null) {
                gabToUpdate.setContent(gab.getContent());
                gabToUpdate.setMediaUrl(gab.getMediaUrl());
                return new GabResult(gabToUpdate, RequestStatus.OK);
            }
            return new GabResult(null, RequestStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new GabResult(null, RequestStatus.BAD_REQUEST);
        }
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
