package za.co.staffschedule.request;

import lombok.Data;
import za.co.staffschedule.dto.UserDTO;

@Data
public class UserRequestRequest {
    private UserDTO userDetails;
}
