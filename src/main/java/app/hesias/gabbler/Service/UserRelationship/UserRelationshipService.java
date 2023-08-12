package app.hesias.gabbler.Service.UserRelationship;

import app.hesias.gabbler.Model.Entity.User;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import app.hesias.gabbler.Model.Entity.UserRelationshipType;

public interface UserRelationshipService {
    UserRelationshipType getRelationshipType(User user1, User user2);
    UserRelationship getByUser1AndUser2(int user1, int user2);
    UserRelationship createUserRelationship (UserRelationship userRelationship);
    UserRelationship updateUserRelationship (UserRelationship userRelationship);
    UserRelationship deleteUserRelationship (UserRelationship userRelationship);

}
