package app.hesias.gabbler.Service.Gab;

import app.hesias.gabbler.Model.Entity.Gab;

import java.util.List;

public interface GabService {
    List<Gab> getAllGabs();
    Gab getGabById(int id);
    Gab createGab(Gab gab);
    Gab updateGab(int id, Gab gab);
    Gab deleteGab(int id);
}
