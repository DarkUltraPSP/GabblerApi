package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class GabResult {
    private Gab gab;
    private RequestStatus requestStatus;
}
