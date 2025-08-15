package com.example.backend.controller;

import com.example.backend.model.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping
    public ResponseEntity<User> create(@RequestBody User u) {
        return ResponseEntity.ok(userService.create(u));
    }

    @GetMapping
    public List<User> list() { return userService.list(); }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) {
        return userService.get(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User patch) {
        return ResponseEntity.ok(userService.update(id, patch));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
