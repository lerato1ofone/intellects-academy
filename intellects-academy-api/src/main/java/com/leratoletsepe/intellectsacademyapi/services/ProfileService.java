package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements com.leratoletsepe.intellectsacademyapi.services.interfaces.ProfileService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserProfileById(Integer userId) throws IaNotFoundException {
        System.out.println(userId);
        User user =  userRepository.getUserProfileById(userId);

        if(user == null)
            throw new IaNotFoundException("User not found");

        return user;
    }
}
