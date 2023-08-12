package app.hesias.gabbler.Service.UserRelationshipType;

import app.hesias.gabbler.Model.Entity.UserRelationshipType;

public interface UserRelationshipTypeService {
    UserRelationshipType createUserRelationshipType(UserRelationshipType userRelationshipType);
    UserRelationshipType getUserRelationshipTypeByLibelle(String libelle);
}
