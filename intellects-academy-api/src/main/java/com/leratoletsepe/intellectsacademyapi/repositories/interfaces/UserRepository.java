package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.dto.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.enums.UserType.UserRole;

import java.util.List;

public interface UserRepository {

    Integer create(String title, String firstName, String lastName, String email, String password, UserRole role) throws IaBadRequestException;

    User findByEmailAndPassword(String email, String password);

    Integer getCountByEmail(String email);

    User findById(Integer userId) throws IaNotFoundException;

    com.leratoletsepe.intellectsacademyapi.models.User getUserProfileById(Integer userId) throws IaNotFoundException;
}