package me.authapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.RoleEntity;
import me.authapp.entity.UserEntity;
import me.authapp.enumpackage.UserRole;
import me.authapp.mapper.UserMapper;
import me.authapp.repository.RoleRepository;
import me.authapp.repository.UserRepository;
import me.authapp.service.UserService;
import me.authapp.util.EncodeDecodeUtil;
import me.authapp.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final UserMapper userMapper;

    @Override
    public UserEntity register(UserRegistrationRequestDto payload) {
        UserEntity newUserEntity = userMapper.userRegistrationRequestDtoToUserEntity(payload);
        newUserEntity.setPassword(EncodeDecodeUtil.encodePassword(payload.getPassword()));

        String guestRole = UserRole.GUEST.toString();
        RoleEntity guestRoleEntity = roleRepository.getByRoleCode(guestRole)
                .orElseThrow(() -> new RuntimeException("Role: " + guestRole + " doesn't exist in DB"));
        newUserEntity.setRoleEntity(guestRoleEntity);
        newUserEntity.setToken(TokenUtil.generateGuestToken(guestRoleEntity.getRoleCode()));

        return userRepository.save(newUserEntity);
    }
}
