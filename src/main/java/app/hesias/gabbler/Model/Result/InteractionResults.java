package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Builder
@Data
public class InteractionResults {
    private List<Interaction> interactions;
    private RequestStatus requestStatus;
}

