package app.hesias.gabbler.Service.UserRelationshipType;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.UserRelationshipType;
import app.hesias.gabbler.Model.Result.UserRelationshipTypeResult;
import app.hesias.gabbler.Repository.UserRelationshipTypeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRelationshipTypeServiceImpl implements UserRelationshipTypeService {
    private final UserRelationshipTypeRepo userRelationshipTypeRepository;
    @Override
    public UserRelationshipTypeResult createUserRelationshipType(UserRelationshipType userRelationshipType) {
        try {
            userRelationshipTypeRepository.save(userRelationshipType);
            return UserRelationshipTypeResult.builder()
                    .userRelationshipType(userRelationshipType)
                    .build();
        } catch (Exception e) {
            return UserRelationshipTypeResult.builder()
                    .build();
        }
    }

    @Override
    public UserRelationshipTypeResult getUserRelationshipTypeByLibelle(String libelle) {
        try {
            UserRelationshipType userRelationshipType = userRelationshipTypeRepository.findByLibelle(libelle).orElseThrow(EntityNotFoundException::new);
            return UserRelationshipTypeResult.builder()
                    .userRelationshipType(userRelationshipType)
                    .build();
        } catch (Exception e) {
            return UserRelationshipTypeResult.builder()
                    .requestStatus(RequestStatus.NOT_FOUND)
                    .build();
        }
    }
}
