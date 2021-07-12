package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.dto.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType;
import com.leratoletsepe.intellectsacademyapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserService implements com.leratoletsepe.intellectsacademyapi.services.interfaces.UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User registerUser(String title, String firstName, String lastName, String email, String password, UserType.UserRole role) throws IaBadRequestException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null)
            email = email.toLowerCase();

        if(!pattern.matcher(email).matches())
            throw new IaBadRequestException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new IaBadRequestException("Email already in use");

        Integer userId = userRepository.create(title, firstName, lastName, email, password, role);

        return userRepository.findById(userId);
    }

    @Override
    public User getUserById(Integer userId) throws IaNotFoundException {
        User user =  userRepository.findById(userId);

        if(user == null)
            throw new IaNotFoundException("User not found");

        return user;
    }
}