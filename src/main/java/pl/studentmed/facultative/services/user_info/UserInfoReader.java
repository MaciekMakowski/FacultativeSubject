package pl.studentmed.facultative.services.user_info;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.studentmed.facultative.exceptions.EntityNotFoundException;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.models.user_info.UserInfoLoginRequestDTO;
import pl.studentmed.facultative.models.user_info.UserInfoLoginResponseDTO;
import pl.studentmed.facultative.services.user_security.SessionRegistry;

@Component
@RequiredArgsConstructor
class UserInfoReader {

    private final UserInfoRepository repository;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final AuthenticationManager authenticationManager;
    private final SessionRegistry sessionRegistry;


    public UserInfo getUserInfoById(Long userInfoId) {
        return repository.findById(userInfoId)
                .orElseThrow(
                        () -> new EntityNotFoundException("userInfo", "User info with id " + userInfoId + " doesn't exist.")
                );
    }
    public UserInfo getUserInfoByEmail(String userEmail) {
        return repository.findByEmail(userEmail)
                .orElseThrow(
                        () -> new EntityNotFoundException("userInfo", "User info with email " + userEmail + " doesn't exist.")
                );
    }
    public boolean existsByEmailOrPesel(String email, String pesel) {
        return repository.existsByEmailOrPesel(email, pesel);
    }
    public void existsByEmail(String email) {
        if (!repository.existsByEmail(email)) {
            throw new BadCredentialsException("There is no such user with given email.");
        }
    }
    public void checkCredentials(String email, String password) {
        if (!passwordEncoder.matches(password, repository.findRegisteredUserInfoByEmail(email).getPassword())) {
            throw new BadCredentialsException("You have written bad email or password.");
        }
    }
    public UserInfo getRegisteredUserInfoByEmail(String userEmail) {
        return repository.findRegisteredUserInfoByEmail(userEmail);
    }
    public UserInfoLoginResponseDTO loginUser(UserInfoLoginRequestDTO userInfoLoginRequestDTO) {
        existsByEmail(userInfoLoginRequestDTO.email());
        checkCredentials(userInfoLoginRequestDTO.email(), userInfoLoginRequestDTO.password());

        var userToLoginInto = getRegisteredUserInfoByEmail(userInfoLoginRequestDTO.email());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userInfoLoginRequestDTO.email(),
                userInfoLoginRequestDTO.password())
        );

        final String sessionId = sessionRegistry.registerSession(userInfoLoginRequestDTO.email());

        return UserInfoLoginResponseDTO.builder()
                .userInfoId(String.valueOf(userToLoginInto.getId()))
                .sessionId(sessionId)
                .email(userToLoginInto.getEmail())
                .firstName(userToLoginInto.getFirstName())
                .lastName(userToLoginInto.getLastName())
                .pesel(userToLoginInto.getPesel())
                .role(userToLoginInto.getRole().value)
                .build();

    }

}
