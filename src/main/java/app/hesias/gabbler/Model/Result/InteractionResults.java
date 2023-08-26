package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Interaction;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class InteractionResults {
    private List<Interaction> interactions;
    private RequestStatus requestStatus;
}

