package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Result.GabResult;
import app.hesias.gabbler.Model.Result.GabResults;
import app.hesias.gabbler.Repository.GabRepo;
import app.hesias.gabbler.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class GabServiceImpl implements GabService{
    private final UserRepo userRepo;
    private final GabRepo gabRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<Gab> getAllGabs() {
        return gabRepo.findAll();
    }

    @Override
    public GabResult getGabById(int id) {
        try {
            Gab gab = gabRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            return new GabResult(gab, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new GabResult(null, RequestStatus.NOT_FOUND);
        }
    }

    @Override
    public GabResults getGabsByUserId(int id) {
        try {
            userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            Optional<List<Gab>> gabs = gabRepo.findGabsByUserId(id);
            return new GabResults(gabs.orElseThrow(EntityNotFoundException::new), RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new GabResults(null, RequestStatus.NOT_FOUND);
            }
            return new GabResults(null, RequestStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public GabResult createGab(Gab gab) {
        try {
            User author = (userRepo.findById(gab.getUser().getIdUser()).orElseThrow(EntityNotFoundException::new));
            if (gab.getContent() == null) throw new Exception("Content and MediaUrl cannot be null");
            gabRepo.save(gab);
            gab.setUser(author);
            return new GabResult(gab, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new GabResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public GabResult updateGab(int id, Gab gab) {
        try {
            Gab gabToUpdate = gabRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            modelMapper.map(gab, gabToUpdate);
            gabRepo.save(gabToUpdate);
            return new GabResult(gabToUpdate, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new GabResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new GabResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public GabResult deleteGab(int id) {
        try {
            Gab gabToDelete = gabRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            gabRepo.delete(gabToDelete);
            return new GabResult(gabToDelete, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new GabResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new GabResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
