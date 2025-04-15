package com.example.boilerplatepractice.domain.users.service;

import com.example.boilerplatepractice.domain.users.dto.SignupRequestDTO;
import com.example.boilerplatepractice.domain.users.dto.UserResponseDTO;
import com.example.boilerplatepractice.domain.users.entity.User;
import com.example.boilerplatepractice.domain.users.entity.UserRole;
import com.example.boilerplatepractice.domain.users.entity.UserStatus;
import com.example.boilerplatepractice.domain.users.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO createUser(@Valid SignupRequestDTO requestDTO) {
        String username = requestDTO.getUsername();
        String password = passwordEncoder.encode(requestDTO.getPassword());
        String email = requestDTO.getEmail();
        String phoneNumber = requestDTO.getPhoneNumber();

        // username 중복검증
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("아이디가 이미 존재합니다.");
        }

        // emal 중복검증
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이메일이 이미 존재합니다.");
        }

        // user 객체 생성
        User user = User.builder()
                .username(username)
                .password(password)
                .email(email)
                .phoneNumber(phoneNumber)
                .role(UserRole.USER)
                .status(UserStatus.ACTIVATED)
                .build();

        // save
        userRepository.save(user);

        return new UserResponseDTO(user);
    }
}
