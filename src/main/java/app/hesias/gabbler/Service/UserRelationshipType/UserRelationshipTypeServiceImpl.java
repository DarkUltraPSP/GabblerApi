package app.hesias.gabbler.Service.UserRelationshipType;

import app.hesias.gabbler.Model.UserRelationshipType;
import app.hesias.gabbler.Repository.UserRelationshipTypeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRelationshipTypeServiceImpl implements UserRelationshipTypeService {
    private final UserRelationshipTypeRepo userRelationshipTypeRepository;
    @Override
    public UserRelationshipType createUserRelationshipType(UserRelationshipType userRelationshipType) {
        return userRelationshipTypeRepository.save(userRelationshipType);
    }

    @Override
    public UserRelationshipType getUserRelationshipTypeByLibelle(String libelle) {
        return userRelationshipTypeRepository.findByLibelle(libelle);
    }
}
