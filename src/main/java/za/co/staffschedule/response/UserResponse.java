package za.co.staffschedule.response;

import lombok.Data;
import za.co.staffschedule.dto.UserDTO;

import java.util.List;

@Data
public class UserResponse {
    List<UserDTO> users;
    UserDTO user;

    public UserResponse(UserDTO user) {
        this.user = user;
    }

    public UserResponse(List<UserDTO> users) {
        this.users = users;
    }
}
