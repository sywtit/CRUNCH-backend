package com.crunch.crunch_server.domain.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    private UserService service;

    ///user/account/signup
    @PostMapping("/user/account/signup")
    @ResponseStatus(value=HttpStatus.OK)
    public User addUser(@RequestBody UserDTO userDTO)
    {
        return service.saveUser(userDTO);
    }

    // //have to delete later this is just for developer to check
    // public List<User> addUsers(@RequestBody List<User> users)
    // {
    //     return service.saveUsers(users);
    // }

    ///users/{userId}/mypage
    @GetMapping("/user/{userId}/mypage")
    public User findUserById(@PathVariable int id)
    {
        return service.getUserById(id);
    }

    ///users/{userId}/mypage/update
    @PutMapping("/{userId}/mypage/update")
    public User updateUserInfo(@RequestBody User user)
    {
        return service.updateUser(user);
    }

    ///users/{userId}/mypage
    @DeleteMapping("/{userId}/mypage")
    public String deleteUserById(@PathVariable int id)
    {
        return service.deleteUser(id);
    }

}
