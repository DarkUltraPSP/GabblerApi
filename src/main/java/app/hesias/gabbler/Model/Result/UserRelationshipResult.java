package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRelationshipResult {
    private UserRelationship userRelationship;
    private RequestStatus requestStatus;
}
