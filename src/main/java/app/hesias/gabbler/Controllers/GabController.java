package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Service.Gab.GabService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gab")
@AllArgsConstructor
public class GabController {
    GabService gabService;

    @GetMapping
    public List<Gab> getAllGabs() {
        return gabService.getAllGabs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gab> getGabByUuid(@PathVariable int id) {
        return gabService.getGabById(id) != null ? ResponseEntity.ok(gabService.getGabById(id)) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gab> createGab(@RequestBody Gab gab) {
        Gab toCreate = gab != null ? gabService.createGab(gab) : null;
        return toCreate != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gab> updateGab(@PathVariable int id, @RequestBody Gab gab) {
        Gab toUpdate = gabService.updateGab(id, gab);
        return toUpdate != null ? ResponseEntity.ok(toUpdate) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gab> deleteGab(@PathVariable int id) {
        if (gabService.deleteGab(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }
}
