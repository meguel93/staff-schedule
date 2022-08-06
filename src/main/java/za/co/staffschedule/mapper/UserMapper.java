package za.co.staffschedule.mapper;

import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;
import za.co.staffschedule.dto.UserDTO;
import za.co.staffschedule.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends Converter<User, UserDTO> {

    User convertToDTO(UserDTO userDTO);
}
