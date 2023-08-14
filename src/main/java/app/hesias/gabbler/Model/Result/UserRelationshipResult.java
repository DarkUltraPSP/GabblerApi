package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Entity.UserRelationship;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class UserRelationshipResult {
    private UserRelationship userRelationship;
    private RequestStatus requestStatus;
}
