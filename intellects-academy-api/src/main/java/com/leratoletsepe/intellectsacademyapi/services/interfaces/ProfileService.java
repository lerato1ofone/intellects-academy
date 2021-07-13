package com.leratoletsepe.intellectsacademyapi.services.interfaces;

import com.leratoletsepe.intellectsacademyapi.exceptions.IaNotFoundException;
import com.leratoletsepe.intellectsacademyapi.models.User;

public interface ProfileService {

    User getUserProfileById(Integer userId) throws IaNotFoundException;
}