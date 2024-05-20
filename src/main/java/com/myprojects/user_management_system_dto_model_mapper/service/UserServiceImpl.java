package com.myprojects.user_management_system_dto_model_mapper.service;

import com.myprojects.user_management_system_dto_model_mapper.dto.UserDto;
import com.myprojects.user_management_system_dto_model_mapper.entity.User;
import com.myprojects.user_management_system_dto_model_mapper.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        //dto to jpa entity
        User user= modelMapper.map(userDto,User.class);
        User savedUser =userRepository.save(user);

        //jpa entity to dto
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public Optional<UserDto> getUserById(Long id) {
        Optional<User> user=userRepository.findById(id);
        return Optional.of(modelMapper.map(user,UserDto.class));
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserDto> userDtoList= new ArrayList<UserDto>();
        List<User> userList=userRepository.findAll();
        int userListSize=userList.size();
        for (int i=0;i<userListSize;i++){
            userDtoList.add(modelMapper.map(userList.get(i),UserDto.class));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user=new User(userDto.getUserId(), userDto.getUserName(), userDto.getUserEmail(), userDto.getUserAge());
        User existingUser=userRepository.findById(user.getUserId()).get();
        existingUser.setUserName(user.getUserName());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserAge(user.getUserAge());
        User updatedUser =userRepository.save(user);
        return modelMapper.map(updatedUser,UserDto.class);
    }

    @Override
    public UserDto deleteUser(Long id) {
        User wantToDelete=userRepository.findById(id).get();
        userRepository.deleteById(id);
        return modelMapper.map(wantToDelete,UserDto.class);
    }


}
