package app.hesias.gabbler.Service.UserRelationship;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Model.Result.UserRelationshipResult;
import app.hesias.gabbler.Repository.UserRelationshipRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserRelationshipServiceImpl implements UserRelationshipService {
    private final UserRelationshipRepo userRelationshipRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserRelationshipResult getByUser1AndUser2(int user1, int user2) {
        try {
            UserRelationship relationship = userRelationshipRepository.getByUser1AndUser2(user1, user2).orElseThrow(EntityNotFoundException::new);
            return new UserRelationshipResult(relationship, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new UserRelationshipResult(null, RequestStatus.NOT_FOUND);
        }
    }

    @Override
    public UserRelationshipResult createUserRelationship(UserRelationship userRelationship) {
        try {
            UserRelationship relationship = userRelationshipRepository.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser()).orElseThrow(EntityNotFoundException::new);
            userRelationshipRepository.save(relationship);
            return new UserRelationshipResult(relationship, RequestStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new UserRelationshipResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new UserRelationshipResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public UserRelationshipResult updateUserRelationship(UserRelationship userRelationship) {
        try {
            UserRelationship relationship = userRelationshipRepository.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser()).orElseThrow(EntityNotFoundException::new);
            modelMapper.map(userRelationship, relationship);
            userRelationshipRepository.save(relationship);
            return new UserRelationshipResult(relationship, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new UserRelationshipResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new UserRelationshipResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @Override
    public UserRelationshipResult deleteUserRelationship(UserRelationship userRelationship) {
        try {
            UserRelationship relationship = userRelationshipRepository.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser()).orElseThrow(EntityNotFoundException::new);
            userRelationshipRepository.delete(relationship);
            return new UserRelationshipResult(relationship, RequestStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (e instanceof EntityNotFoundException) {
                return new UserRelationshipResult(null, RequestStatus.NOT_FOUND);
            } else {
                return new UserRelationshipResult(null, RequestStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}
