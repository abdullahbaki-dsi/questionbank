package org.dsi.com.userService.service.Implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsi.com.userService.dto.RoleDto;
import org.dsi.com.userService.dto.RoleRequestDto;
import org.dsi.com.userService.dto.UserRoleDto;
import org.dsi.com.userService.model.Role;
import org.dsi.com.userService.model.UserRoles;
import org.dsi.com.userService.repository.RolesRepository;
import org.dsi.com.userService.repository.UserRolesRepository;
import org.dsi.com.userService.service.RolesService;
import org.dsi.com.userService.utils.Status;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RolesService {

    final RolesRepository rolesRepository;
    final UserRolesRepository userRolesRepository;
    @Override
    public RoleDto getRoleByRoleId(Long rolesID) {

       Optional<Role> roleOptional= rolesRepository.findById(rolesID);

       if(roleOptional.isPresent()){
           Role role =roleOptional.get();
           return mapToRoleDto(role);
       }
       else{
           throw new RuntimeException("Role Id Not Found "+ rolesID);
       }
    }

    @Override
    public List<RoleDto> getAllRoles(){
        List<Role> roles = rolesRepository.findAll();
        Stream<RoleDto> roleDtoStream = roles.stream().map(this::mapToRoleDto);
        return roleDtoStream.toList();

    }

    @Override
    public RoleDto createRole(RoleRequestDto roleRequestDto) {
        Role.RoleBuilder builder = Role.builder();
        builder.name(roleRequestDto.getName());
        builder.status(Status.DRAFT.getCode());
        Role role = builder.build();
        rolesRepository.save(role);
        return  mapToRoleDto(role);
    }

    /**
     * @param userRoleDto 
     * @return
     */
    @Override
    public UserRoles createUserRole(UserRoleDto userRoleDto) {


        UserRoles.UserRolesBuilder builder = UserRoles.builder();
        builder.userId(userRoleDto.getUserId());
        builder.roleId(userRoleDto.getRoleId());
        UserRoles userRoles = builder.build();
        return userRolesRepository.save(userRoles);
    }

    /**
     * @param roleID 
     * @return
     */
    @Override
    public RoleDto activateRole(Long roleID) {
        Optional<Role> roleOptional = rolesRepository.findById(roleID);
        if(roleOptional.isPresent()){
            Role role = roleOptional.get();
            role.setStatus(Status.ACTIVE.getCode());
            rolesRepository.save(role);
            return mapToRoleDto(role);
        }
        else{
            throw new RuntimeException("Role Id Not Found "+ roleID);
        }
    }

    public List<UserRoles> getUserRolesByUserId(Long userId)
    {
        return userRolesRepository.findByUserId(userId);
    }

    private RoleDto mapToRoleDto(Role role) {
        return RoleDto.builder()
                .roleId(role.getId())
                .name(role.getName())
                .status(Status.getDescription(role.getStatus()))
                .build();
    }
}
