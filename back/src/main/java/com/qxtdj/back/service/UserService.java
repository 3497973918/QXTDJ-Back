package com.qxtdj.back.service;

import com.qxtdj.back.model.User;
import com.qxtdj.back.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> listAll() {
        return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return repo.findById(id);
    }

    public User create(User user) {
        // TODO: 密码加密、校验用户名唯一性
        return repo.save(user);
    }

    public User update(Long id, User user) {
        user.setId(id);
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }
}