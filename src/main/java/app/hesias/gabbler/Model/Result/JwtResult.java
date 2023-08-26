package app.hesias.gabbler.Model.Result;

import app.hesias.gabbler.Model.Entity.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtResult {
    private String jwt;
    private RequestStatus requestStatus;
}
