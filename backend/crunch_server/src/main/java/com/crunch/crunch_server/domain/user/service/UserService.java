package com.crunch.crunch_server.domain.user.service;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.crunch.crunch_server.domain.user.dto.UserDTO;
import com.crunch.crunch_server.domain.user.dto.UserInfoDTO;
import com.crunch.crunch_server.domain.user.dto.UserPointDTO;
import com.crunch.crunch_server.domain.user.dto.SessionRequestDTO;
import com.crunch.crunch_server.domain.user.entity.User;
import com.crunch.crunch_server.domain.user.mapper.UserInfoMapper;
import com.crunch.crunch_server.domain.user.mapper.UserMapper;
import com.crunch.crunch_server.domain.user.mapper.UserPointMapper;
import com.crunch.crunch_server.domain.user.respository.UserRepository;
import com.crunch.crunch_server.util.EncryptionUtil;
import com.crunch.crunch_server.util.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    // private UserMapper userMapper;


    @Autowired
    private JwtUtil jwtUtil; 

    //post
    public User saveUser(UserDTO userDTO)
    {
        User user = UserMapper.Instance.toEntity(userDTO);
        return repository.save(user);
    }

    //login
    public String createToken(SessionRequestDTO sessionRequestDTO) throws Exception
    {
        

        User user = repository.findByIdentity(sessionRequestDTO.getIdentity());
        if(user==null) throw new Exception ("user not existed");

        String salt = user.getSalt();
        String password = user.getPassword();

        String getHashPassword = EncryptionUtil.getEncrypt(sessionRequestDTO.getPassword(),salt.getBytes());
        if(!password.equals(getHashPassword))
        {
            throw new Exception("wrong password!");
        }
        else
        {
            return jwtUtil.createToken(sessionRequestDTO.getIdentity(), salt);
        }
        
        
        
    }

    //user info with login
    public UserInfoDTO getUserInfo(String identity)
    {
        User user = repository.findByIdentity(identity);
        return UserInfoMapper.Instance.toUserInfoDTO(user);
    }

    
    
    // //post user list
    // public List<User> saveUsers(List<User> users)
    // {
    //     return repository.saveAll(users);
    // }

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

    //get filter by Identity
    public User getUserByIdentity(String identity)
    {
        return repository.findByIdentity(identity);
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
    
    //get point by Id
    public UserPointDTO getUserById(String id)
    {
        User user = repository.findById(id);
        return UserPointMapper.Instance.toUserPointDTO(user);

    }
}
