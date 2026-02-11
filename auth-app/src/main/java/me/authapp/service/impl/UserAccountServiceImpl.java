package me.authapp.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.authapp.dto.request.UserRegistrationRequestDto;
import me.authapp.entity.UserEntity;
import me.authapp.repository.UserRepository;
import me.authapp.service.UserAccountService;
import me.authapp.util.EncodeDecodeUtil;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    public UserRepository userRepository;

    @Override
    @Transactional
    public UserEntity register(UserRegistrationRequestDto payload) {
        if (userRepository.existsByLogin(payload.getLogin())) {
            throw new RuntimeException("User with login " + payload.getLogin() + " already exists");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(payload.getName());
        userEntity.setLogin(payload.getLogin());
        userEntity.setPassword(EncodeDecodeUtil.encodePassword(payload.getPassword()));
        userEntity.setCreatedAt(LocalDate.now());

        return userRepository.save(userEntity);
    }
}
