package ru.gb.my_first_crud.Service;

import org.springframework.stereotype.Service;
import ru.gb.my_first_crud.Model.User;
import ru.gb.my_first_crud.Repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    public void updateById(int id, User user) {
        userRepository.updateById(id, user);
    }
}