package pl.programujodpodstaw.spring_web_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @GetMapping("posts")
    public ResponseEntity<Iterable<Post>> getAllPosts(){
        Iterable<Post> posts = postRepository.findAll();

        return ResponseEntity.ok(posts);
    }

    @GetMapping("posts/{id}")
    public ResponseEntity<Post> getOnePost(@PathVariable Integer id){
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post){
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(post);
    }
}
