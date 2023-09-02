package app.hesias.gabbler.Controllers;

import app.hesias.gabbler.Model.Entity.Gab;
import app.hesias.gabbler.Model.Entity.RequestStatus;
import app.hesias.gabbler.Model.Result.GabResult;
import app.hesias.gabbler.Model.Result.GabResults;
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
        GabResult gabResult = gabService.getGabById(id);
        return gabResult.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(gabResult.getRequestStatus().getValue()).body(gabResult.getGab())
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Gab>> getGabsByUserId(@PathVariable int id) {
        GabResults gabResults = gabService.getGabsByUserId(id);
        return gabResults.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(gabResults.getRequestStatus().getValue()).body(gabResults.getGabs())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Gab> createGab(@RequestBody Gab gab) {
        GabResult toCreate = gabService.createGab(gab);
        return toCreate.getRequestStatus() == RequestStatus.CREATED ?
                ResponseEntity.status(toCreate.getRequestStatus().getValue()).body(toCreate.getGab())
                : ResponseEntity.status(toCreate.getRequestStatus().getValue()).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gab> updateGab(@PathVariable int id, @RequestBody Gab gab) {
        GabResult toUpdate = gabService.updateGab(id, gab);
        return toUpdate.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(toUpdate.getRequestStatus().getValue()).body(toUpdate.getGab())
                : ResponseEntity.status(toUpdate.getRequestStatus().getValue()).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gab> deleteGab(@PathVariable int id) {
        GabResult gabResult = gabService.deleteGab(id);
        return gabResult.getRequestStatus() == RequestStatus.OK ?
                ResponseEntity.status(gabResult.getRequestStatus().getValue()).body(gabResult.getGab())
                : ResponseEntity.status(gabResult.getRequestStatus().getValue()).build();
    }
}
