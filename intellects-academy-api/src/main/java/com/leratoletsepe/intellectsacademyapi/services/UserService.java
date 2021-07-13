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
    public User registerUser(String title, String firstName, String lastName, String email, String password, String role) throws IaBadRequestException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null)
            email = email.toLowerCase();

        if(!pattern.matcher(email).matches())
            throw new IaBadRequestException("Invalid email format");

        Integer count = userRepository.getCountByEmail(email);
        if(count > 0)
            throw new IaBadRequestException("Email already in use");

        UserType.UserRole userRole = UserType.UserRole.valueOf(role.toUpperCase());

        Integer userId = userRepository.create(title, firstName, lastName, email, password, userRole);

        return userRepository.findById(userId);
    }

    @Override
    public User loginUser(String email, String password) throws IaBadRequestException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        if(email != null)
            email = email.toLowerCase();

        if(!pattern.matcher(email).matches())
            throw new IaBadRequestException("Invalid email format");

        User user = userRepository.findByEmailAndPassword(email, password);

        return user;
    }
}