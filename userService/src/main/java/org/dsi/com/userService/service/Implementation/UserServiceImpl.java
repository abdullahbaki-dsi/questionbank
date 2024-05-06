package org.dsi.com.userService.service.Implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.request.LoginRequestDto;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.model.User;
import org.dsi.com.userService.model.UserRoles;
import org.dsi.com.userService.repository.UserRepository;
import org.dsi.com.userService.service.RolesService;
import org.dsi.com.userService.service.UserRoleService;
import org.dsi.com.userService.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final UserRoleService userRoleService;
    final RolesService rolesService;
    @Override
    public UserResponseDto saveUser(UserCreateDto userCreateDto) {
        User.UserBuilder builder= User.builder();
        User user=  builder.firstName(userCreateDto.getFirstName())
                .lastName(userCreateDto.getLastName())
                .email(userCreateDto.getEmail())
                .userName(userCreateDto.getUserName())
                .password(BCrypt.hashpw(userCreateDto.getPassword(), BCrypt.gensalt()))
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .isDeleted(Boolean.FALSE)
                .build();
        try {
            user = userRepository.save(user);
        }
        catch (DataIntegrityViolationException ex) {
            log.error("Error while saving user", ex);
            throw  ex;
        }
        return  mapToUserResponseDto(user);
    }

    /**
     * @return list of users
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        Stream<UserResponseDto> roleDtoStream = users.stream().map(this::mapToUserResponseDto);
        return roleDtoStream.toList();

    }

    /**
     * @param userID user id to fetch user by
     * @return user response
     */
    @Override
    public UserResponseDto getUserByUserID(Long userID) {
        Optional<User> user = userRepository.findById(userID);
        if(user.isEmpty()) throw new NoSuchElementException("User not found");
        return mapToUserResponseDto(user.get());

    }

    /**
     * @param loginRequestDto login request dto with username and password
     * @return jwt token
     */
    @Override
    public UserResponseDto loginUser(LoginRequestDto loginRequestDto) {
        Optional<User> user = userRepository.findByUserName(loginRequestDto.getUserName());
        if(user.isPresent() && BCrypt.checkpw(loginRequestDto.getPassword(), user.get().getPassword())){
            return mapToUserResponseDto(user.get());
        }
        else{
            throw new DataIntegrityViolationException("Username/Password Incorrect");
        }
    }

    private UserResponseDto mapToUserResponseDto(User user) {

        return UserResponseDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .lastLoginDate(user.getLastLoginDate())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userID(user.getId())
                .roles(getUserRoles(user.getId()))
                .build();

    }

    private List<RoleDto> getUserRoles(Long userId) {
       return userRoleService.getUserRolesByUserID(userId).stream()
                .map(this::mapToRolesObject).toList();
    }

    private RoleDto mapToRolesObject(UserRoles userRoles) {
        return rolesService.getRoleByRoleId(userRoles.getRoleId());
    }


}
