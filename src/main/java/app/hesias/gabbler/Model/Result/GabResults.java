package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class GabResults {
    private List<Gab> gabs;
    private RequestStatus requestStatus;
}
