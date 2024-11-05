package co.escuelaing.edu.controller;

import co.escuelaing.edu.model.Post;
import co.escuelaing.edu.service.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/stream", 
                consumes = MediaType.APPLICATION_JSON_VALUE, 
                produces = MediaType.APPLICATION_JSON_VALUE)
public class StreamController {

    @Autowired
    private StreamService streamService;

    @GetMapping
    public ResponseEntity<?> getPosts() {
        return ResponseEntity.ok(streamService.getStream());
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(streamService.addPost(post));
    }
}
