package za.co.staffschedule.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.staffschedule.exception.UserException;
import za.co.staffschedule.request.UserRequestRequest;
import za.co.staffschedule.response.UserResponse;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "v1/users", produces = "application/json")
@AllArgsConstructor
public class UsersControllerV1 extends BaseController {
    private final UserService userService;

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserResponse> register(UserRequestRequest request) throws UserException {
        UserResponse response = userService.register(request);
        return createdResponse("/", response.getUser().getId(), response);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserDetails(@PathVariable("userId") Long userId) throws UserException {
        UserResponse response = userService.getUserDetails(userId);
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<UserResponse> getUsers() throws UserException {
        UserResponse response = userService.getUsers();
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @PatchMapping
    public ResponseEntity<UserResponse> updateUser(UserRequestRequest request) throws UserException {
        UserResponse response = userService.update(request);
        return okResponse(response);
    }

}
