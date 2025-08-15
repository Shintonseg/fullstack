package com.example.backend.service;

import com.example.backend.model.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User create(User u) {
        u.setId(null);
        try {
            u.setCreatedAt(Instant.now());
            return userRepository.save(u);
        } catch (DuplicateKeyException dke) {
            throw new IllegalArgumentException("Email already exists");
        }
    }

    public Optional<User> get(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public User update(String id, User patch) {
        return userRepository.findById(id).map(existing -> {
            if (patch.getEmail() != null && !patch.getEmail().equals(existing.getEmail())) {
                if (userRepository.existsByEmail(patch.getEmail())) {
                    throw new IllegalArgumentException("Email already exists");
                }
                existing.setEmail(patch.getEmail());
            }
            if (patch.getName() != null) existing.setName(patch.getName());
            if (patch.getRole() != null) existing.setRole(patch.getRole());
            return userRepository.save(existing);
        }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
