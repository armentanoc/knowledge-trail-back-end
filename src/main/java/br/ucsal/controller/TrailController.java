package br.ucsal.controller;

import br.ucsal.domain.trails.Trail;
import br.ucsal.domain.videos.Video;
import br.ucsal.dto.trails.*;
import br.ucsal.service.interfaces.ITrailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trails")
@Tag(name = "Trails", description = "Operations related to trail management.")
public class TrailController {

    @Autowired
    private ITrailService trailService;

    @PostMapping
    @Operation(summary = "Create a trail")
    public ResponseEntity<Trail> create(@RequestBody TrailMinimal trail) {
        Trail createdTrail = trailService.create(trail);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTrail);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a trail")
    public ResponseEntity<Trail> update(@PathVariable Long id, @RequestBody TrailMinimal updatedData) {
        Trail updatedTrail = trailService.update(id, updatedData);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTrail);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a trail")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        trailService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/videos")
    @Operation(summary = "Add videos to a trail")
    public ResponseEntity<Trail> addVideos(@PathVariable Long id, @RequestBody List<VideoMinimal> videos) {
        Trail updatedTrail = trailService.addVideos(id, videos);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTrail);
    }

    @PutMapping("/{id}/videos")
    @Operation(summary = "Replace videos in a trail")
    public ResponseEntity<Trail> replaceVideos(@PathVariable Long id, @RequestBody List<VideoMinimal> videos) {
        Trail updatedTrail = trailService.replaceVideos(id, videos);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTrail);
    }

    @GetMapping
    @Operation(summary = "Get trails")
    public ResponseEntity<Page<Trail>> getTrails(
        @RequestParam(name = "skillId", required = false) 
        Long skillId, 
        Pageable pageable) {

        Page<Trail> trails;
        
        if (skillId != null) {
            trails = trailService.findBySkill(skillId, pageable);
        } else {
            trails = trailService.findAll(pageable);
        }

        return ResponseEntity.ok(trails);
    }

    @GetMapping("/{id}/videos")
    @Operation(summary = "Get videos of a trail")
    public ResponseEntity<List<Video>> getVideos(@PathVariable Long id) {
        List<Video> videos = trailService.getVideos(id);
        return ResponseEntity.status(HttpStatus.OK).body(videos);
    }

    @GetMapping("/{id}/skill")
    @Operation(summary = "Get skill of a trail")
    public ResponseEntity<?> getSkill(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(trailService.getSkill(id));
    }

}
