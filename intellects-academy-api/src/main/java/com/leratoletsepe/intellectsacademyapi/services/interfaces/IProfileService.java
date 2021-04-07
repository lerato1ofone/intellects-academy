package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserProfileDto;

public interface IProfileService {
    UserProfileDto getUserProfileById(Integer userId) throws IaNotFoundException;

    UserProfileDto updateProfile(Integer userId, User user) throws IaBadRequestException;
}