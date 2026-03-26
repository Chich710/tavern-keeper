package me.authapp.service.impl;

import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.AuthorizationRequestDto;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;
import me.authapp.mapper.UserMapper;
import me.authapp.repository.UserRepository;
import me.authapp.service.AccountService;
import me.authapp.util.EncodeDecodeUtil;
import me.authapp.util.TokenUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    public final UserRepository userRepository;

    @Override
    public void login(AuthorizationRequestDto payload) {
        UserEntity userEntity = userRepository.getByLogin(payload.getLogin()).orElseThrow(() -> new RuntimeException("Wrong login or password"));
        if (!EncodeDecodeUtil.checkPassword(payload.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException("Wrong login or password");
        }
        userEntity.setToken(TokenUtil.generateGuestToken(userEntity.getRoleEntity().getRoleCode()));
        userRepository.save(userEntity);
    }
}
