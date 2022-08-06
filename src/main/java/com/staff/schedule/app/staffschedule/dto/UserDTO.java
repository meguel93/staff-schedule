package com.staff.schedule.app.staffschedule.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private List<RoleDTO> roles;
}
