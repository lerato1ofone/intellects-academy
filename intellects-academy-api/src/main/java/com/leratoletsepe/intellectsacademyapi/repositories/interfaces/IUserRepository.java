package com.leratoletsepe.intellectsacademyapi.repositories.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaBadRequestException;
import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.User;
import com.leratoletsepe.intellectsacademyapi.models.dto.UserDto;
import com.leratoletsepe.intellectsacademyapi.models.enums.UserType.UserRole;

public interface IUserRepository {

    Integer create(String title, String firstName, String lastName, String email, String password, UserRole role) throws IaBadRequestException;

    UserDto findByEmailAndPassword(String email, String password);

    Integer getCountByEmail(String email);

    UserDto findById(Integer userId) throws IaNotFoundException;

    User getUserProfileById(Integer userId) throws IaNotFoundException;

    void update(Integer userId, User User) throws IaBadRequestException;

    void remove(Integer userId) throws IaBadRequestException;
}