package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GabResult {
    private Gab gab;
    private RequestStatus requestStatus;
}
