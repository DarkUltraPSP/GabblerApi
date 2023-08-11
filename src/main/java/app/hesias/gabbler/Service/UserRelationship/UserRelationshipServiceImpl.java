package app.hesias.gabbler.Service.UserRelationship;

import app.hesias.gabbler.Model.User;
import app.hesias.gabbler.Model.UserRelationship;
import app.hesias.gabbler.Model.UserRelationshipType;
import app.hesias.gabbler.Repository.UserRelationshipRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserRelationshipServiceImpl implements UserRelationshipService {
    private final UserRelationshipRepo userRelationshipRepository;

    @Override
    public UserRelationshipType getRelationshipType(User user1, User user2) {
        return userRelationshipRepository.getRelationshipType(user1.getIdUser(), user2.getIdUser());
    }

    @Override
    public UserRelationship getByUser1AndUser2(int user1, int user2) {
        return userRelationshipRepository.getByUser1AndUser2(user1, user2);
    }

    @Override
    public UserRelationship createUserRelationship(UserRelationship userRelationship) {
        userRelationship.setCreatedAt(LocalDateTime.now());
        return userRelationshipRepository.save(userRelationship);
    }

    @Override
    public UserRelationship updateUserRelationship(UserRelationship userRelationship) {
        UserRelationship userRelationshipToUpdate = userRelationshipRepository.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser());
        userRelationshipToUpdate.setUserRelationshipType(userRelationship.getUserRelationshipType());
        return userRelationshipRepository.save(userRelationshipToUpdate);
    }

    @Override
    public UserRelationship deleteUserRelationship(UserRelationship userRelationship) {
        UserRelationship userRelationshipToDelete = userRelationshipRepository.getByUser1AndUser2(userRelationship.getUser1().getIdUser(), userRelationship.getUser2().getIdUser());
        if (userRelationshipToDelete == null) {
            return null;
        }
        userRelationshipRepository.delete(userRelationshipToDelete);
        return userRelationshipToDelete;
    }
}
