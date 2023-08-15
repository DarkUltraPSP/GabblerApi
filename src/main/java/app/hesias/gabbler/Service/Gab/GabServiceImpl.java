package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.GabResult;
import app.hesias.gabbler.Repository.GabRepo;
import app.hesias.gabbler.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
            return GabResult.builder()
                    .gab(gab)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return GabResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }

    @Override
    public GabResult createGab(Gab gab) {
        try {
            if (gab.getContent() == null || gab.getMediaUrl() == null) throw new Exception("Content and MediaUrl cannot be null");
            gabRepo.save(gab);
            return GabResult.builder()
                    .gab(gab)
                    .requestStatus(RequestStatus.CREATED)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return GabResult.builder()
                    .requestStatus(RequestStatus.BAD_REQUEST)
                    .build();
        }
    }

    @Override
    public GabResult updateGab(int id, Gab gab) {
        try {
            Gab gabToUpdate = gabRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            modelMapper.map(gab, gabToUpdate);
            gabRepo.save(gabToUpdate);
            return GabResult.builder()
                    .gab(gabToUpdate)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return GabResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return GabResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }

    @Override
    public GabResult deleteGab(int id) {
        try {
            Gab gabToDelete = gabRepo.findById(id).orElseThrow(EntityNotFoundException::new);
            gabRepo.delete(gabToDelete);
            return GabResult.builder()
                    .gab(gabToDelete)
                    .requestStatus(RequestStatus.OK)
                    .build();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return GabResult.builder()
                        .requestStatus(RequestStatus.NOT_FOUND)
                        .build();
            } else {
                return GabResult.builder()
                        .requestStatus(RequestStatus.INTERNAL_SERVER_ERROR)
                        .build();
            }
        }
    }
}
