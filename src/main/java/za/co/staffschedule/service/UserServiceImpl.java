package za.co.staffschedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.staffschedule.dto.RoleDTO;
import za.co.staffschedule.dto.UserDTO;
import za.co.staffschedule.exception.ConflictException;
import za.co.staffschedule.exception.NotFoundException;
import za.co.staffschedule.exception.UserException;
import za.co.staffschedule.mapper.UserMapper;
import za.co.staffschedule.model.Role;
import za.co.staffschedule.model.User;
import za.co.staffschedule.repository.RoleRepository;
import za.co.staffschedule.repository.UserRepository;
import za.co.staffschedule.request.UserRequestRequest;
import za.co.staffschedule.response.UserResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserDetailsService, UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponse register(UserRequestRequest request) throws UserException {
        UserDTO userDetails = request.getUserDetails();
        validateUser(userDetails);
        User newUser = userMapper.convertToDTO(userDetails);
//        newUser.setPassword(passwordEncoder.encode(newUser.getPassword())); Encode password
        addUserRolesIfPossible(userDetails, newUser);
        newUser = userRepository.save(newUser);
        log.info(String.format("User has been created successfully %s", newUser.getId()));
        return getUserResponse(newUser);
    }

    private void addUserRolesIfPossible(UserDTO userDetails, User newUser) {
        List<Long> ids = userDetails.getRoles().stream().map(RoleDTO::getId).collect(Collectors.toList());
        List<Role> rolesToLink = roleRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(rolesToLink)) return;
        newUser.setRoles(rolesToLink);
    }

    private UserResponse getUserResponse(User user) {
        return new UserResponse(userMapper.convert(user));
    }

    @Override
    public UserResponse update(UserRequestRequest request) throws UserException {
        UserDTO userDetails = request.getUserDetails();
        fetchUserById(userDetails.getId());
        User updated = userMapper.convertToDTO(userDetails);
        updated = userRepository.save(updated);
        return getUserResponse(updated);
    }

    @Override
    public void delete(Long id) throws UserException {
        User user = fetchUserById(id);
        userRepository.delete(user);
    }

    @Override
    public UserResponse getUserDetails(Long id) throws UserException {
        User user = fetchUserById(id);
        return new UserResponse(userMapper.convert(user));
    }

    @Override
    public UserResponse getUsers() throws UserException {
        List<User> users = userRepository.findAll();
        List<UserDTO> convertedUsers = users.stream().map(userMapper::convert).collect(Collectors.toList());
        return new UserResponse(convertedUsers);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toCollection(HashSet::new));
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }

    private User fetchUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) throw new NotFoundException("User not found");
        return userOptional.get();
    }

    private void validateUser(UserDTO userDetails) {
        User user = userRepository.findByEmail(userDetails.getEmail());
        if (Objects.nonNull(user)) throw new ConflictException("User already exist");
    }

}
