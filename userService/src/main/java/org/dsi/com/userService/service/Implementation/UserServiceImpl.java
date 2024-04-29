package org.dsi.com.userService.service.Implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.UserCreateDto;
import org.dsi.com.userService.dto.UserResponseDto;
import org.dsi.com.userService.model.Role;
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
                .password(userCreateDto.getPassword())
                .createdDate(new Date())
                .lastModifiedDate(new Date())
                .build();
        try {
            user = userRepository.save(user);
        }
        catch (DataIntegrityViolationException ex) {
            throw  ex;
        }
        return  mapToUserResponseDto(user);
    }

    /**
     * @return 
     */
    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        Stream<UserResponseDto> roleDtoStream = users.stream().map(this::mapToUserResponseDto);
        return roleDtoStream.toList();

    }

    /**
     * @param userID 
     * @return
     */
    @Override
    public UserResponseDto getUserByUserID(Long userID) {
        Optional<User> user = userRepository.findById(userID);
            return  mapToUserResponseDto(user.get());

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
