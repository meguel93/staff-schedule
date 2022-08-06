package za.co.staffschedule.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import za.co.staffschedule.exception.UserException;
import za.co.staffschedule.request.UserRequestRequest;
import za.co.staffschedule.response.UserResponse;

@Service
public interface UserService extends UserDetailsService {

    UserResponse register(UserRequestRequest userRegistrationRequest) throws UserException;

    UserResponse update(UserRequestRequest userRegistrationRequest) throws UserException;

    void delete(Long id) throws UserException;

    UserResponse getUserDetails(Long id) throws UserException;

    UserResponse getUsers() throws UserException;
}
