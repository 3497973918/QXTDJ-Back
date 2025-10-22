package com.qxtdj.back.controller;

import com.qxtdj.back.model.User;
import com.qxtdj.back.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;
    public UserController(UserService service) { this.service = service; }

    @GetMapping
    public List<User> list() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return service.findById(id)
                .map(u -> ResponseEntity.ok(u))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 简单登录示例（生产应使用加密 + token）
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User payload) {
        return service.findByUsername(payload.getUsername())
                .filter(u -> u.getPassword().equals(payload.getPassword()))
                .map(u -> ResponseEntity.ok(Map.of("success", true, "user", u)))
                .orElse(ResponseEntity.status(401).body(Map.of("success", false, "message", "invalid credentials")));
    }
}