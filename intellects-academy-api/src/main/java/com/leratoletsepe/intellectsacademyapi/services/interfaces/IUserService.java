package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserDto;

public interface IUserService {

    UserDto registerUser(String title, String firstName, String lastName, String email, String password, String role) throws IaBadRequestException;

    UserDto validateUser(String email, String password) throws IaBadRequestException;

    void deleteAccount(Integer userId) throws IaBadRequestException;
}