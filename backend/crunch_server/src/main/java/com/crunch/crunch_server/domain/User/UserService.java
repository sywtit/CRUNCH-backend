package com.crunch.crunch_server.domain.User;

import com.crunch.crunch_server.domain.User.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    //post
    public User saveUser(User user)
    {
        return repository.save(user);
    }

    //post user list
    public List<User> saveUsers(List<User> users)
    {
        return repository.saveAll(users);
    }

    //get all
    public List<User> getUsers()
    {
        return repository.findAll();
    }

    //get filter by id
    public User getUserById(int id)
    {
        return repository.findById(id).orElse(null);
    }

    //get filter by name, don't need this but just for test
    //findByName method will be created in UserRepository
    public User getUserByName(String name)
    {
        return repository.findByName(name);
    }

    //delete user
    public String deleteUser(int id)
    {
        repository.deleteById(id);
        return "user removed => " +id;
    }

    //update user name
    public User updateUser(User user)
    {
        User existingUser=repository.findById(user.getId()).orElse(null);
        existingUser.setName(user.getName());
        return repository.save(existingUser);
    }
}
