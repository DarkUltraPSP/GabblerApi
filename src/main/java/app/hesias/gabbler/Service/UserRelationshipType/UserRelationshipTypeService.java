package app.hesias.gabbler.Service.UserRelationshipType;

import app.hesias.gabbler.Model.Entity.UserRelationshipType;
import app.hesias.gabbler.Model.Result.UserRelationshipTypeResult;

public interface UserRelationshipTypeService {
    UserRelationshipTypeResult createUserRelationshipType(UserRelationshipType userRelationshipType);
    UserRelationshipTypeResult getUserRelationshipTypeByLibelle(String libelle);
}
