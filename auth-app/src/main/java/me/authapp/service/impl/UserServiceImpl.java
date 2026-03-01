package me.authapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.RoleEntity;
import me.authapp.entity.UserEntity;
import me.authapp.enumpackage.UserRole;
import me.authapp.exeption.UserException;
import me.authapp.mapper.UserMapper;
import me.authapp.repository.RoleRepository;
import me.authapp.repository.UserRepository;
import me.authapp.service.UserService;
import me.authapp.util.EncodeDecodeUtil;
import me.authapp.util.TokenUtil;
import org.springframework.stereotype.Service;
import static me.authapp.exeption.UserException.ROLE_DOES_NOT_EXIST;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepository userRepository;
    public final RoleRepository roleRepository;
    public final UserMapper userMapper;

    @Override
    public UserEntity register(UserRegistrationRequestDto payload) {
        RoleEntity guestRoleEntity = roleRepository.getByRoleCode(
                UserRole.GUEST.toString()
                ).orElseThrow(() -> new UserException(ROLE_DOES_NOT_EXIST, 600));
        payload.setPassword(EncodeDecodeUtil.encodePassword(payload.getPassword()));
        UserEntity newUserEntity = userMapper.userRegistrationRequestDtoToUserEntity(payload);

        newUserEntity.setRoleEntity(guestRoleEntity);
        newUserEntity.setToken(TokenUtil.generateGuestToken(guestRoleEntity.getRoleCode()));

        return userRepository.save(newUserEntity);
    }
}
