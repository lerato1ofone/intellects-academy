package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.dto.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType;

public interface UserService {

    User registerUser(String title, String firstName, String lastName, String email, String password, String role) throws IaBadRequestException;

    User validateUser(String email, String password) throws IaBadRequestException;
}