package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Result.GabResult;

import java.util.List;

public interface GabService {
    List<Gab> getAllGabs();
    GabResult getGabById(int id);
    GabResult createGab(Gab gab);
    GabResult updateGab(int id, Gab gab);
    GabResult deleteGab(int id);
}
