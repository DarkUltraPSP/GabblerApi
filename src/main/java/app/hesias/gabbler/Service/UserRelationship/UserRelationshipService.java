package app.hesias.gabbler.Service.UserRelationship;

import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Model.Entity.UserRelationshipType;
import app.hesias.gabbler.Model.Result.UserRelationshipResult;

public interface UserRelationshipService {
    UserRelationshipResult getByUser1AndUser2(int user1, int user2);
    UserRelationshipResult createUserRelationship (UserRelationship userRelationship);
    UserRelationshipResult updateUserRelationship (UserRelationship userRelationship);
    UserRelationshipResult deleteUserRelationship (UserRelationship userRelationship);

}
