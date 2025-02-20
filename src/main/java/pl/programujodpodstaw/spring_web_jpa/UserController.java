package pl.programujodpodstaw.spring_web_jpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
public class UserController {
//    tutaj będziemy przekazywać UserRepository, bo będziemy go używać do zarządzania użytkownikami w bazie danych
//    to jest ta różnica między poprzednim webservic-em, w którym naszą bazą danych był nasz plik tekstowy
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("users")
    public ResponseEntity<Iterable<User>> getAllUsers(){
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable Integer id){
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user){
        userRepository.save(user);  //dodanie usera do bazy danych
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();   //całe to służy do stworzenia lokacji pod którą ten nowy user będzie dostępny

        return ResponseEntity.created(location).body(user); //tu musimy przekazać tą lokację i nowego usera
    }
}
