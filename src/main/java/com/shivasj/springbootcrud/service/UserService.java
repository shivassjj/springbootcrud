package com.shivasj.springbootcrud.service;

import com.shivasj.springbootcrud.model.User;
import com.shivasj.springbootcrud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByIdUsers(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public void updateUser(int id, User user) {
        User userFromDb = findByIdUsers(id);
        userFromDb.setName(user.getName());
        userFromDb.setEmail(user.getEmail());
        userFromDb.setAddress(user.getAddress());
        userRepository.save(userFromDb);
    }

    public void deleteByIdUsers(int id) {
        userRepository.deleteById(id);
    }
}
