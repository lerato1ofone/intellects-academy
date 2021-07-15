package com.leratoletsepe.intellectsacademyapi.services;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserProfileDto;
import com.leratoletsepe.intellectsacademyapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements com.leratoletsepe.intellectsacademyapi.services.interfaces.ProfileService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserProfileDto getUserProfileById(Integer userId) throws IaNotFoundException {
        User user =  userRepository.getUserProfileById(userId);

        if(user == null)
            throw new IaNotFoundException("User not found");

        return new UserProfileDto(
                user.getUserId(),
                user.getTitle(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getUserRole(),
                user.getDob(),
                user.getSemesterMark(),
                user.getOfficeNumber(),
                user.getAvatar(),
                user.getNotes(),
                user.getCourses()
        );
    }

    @Override
    public UserProfileDto updateProfile(Integer userId, User user) throws IaBadRequestException {
        userRepository.update(userId, user);

        return getUserProfileById(userId);
    }
}