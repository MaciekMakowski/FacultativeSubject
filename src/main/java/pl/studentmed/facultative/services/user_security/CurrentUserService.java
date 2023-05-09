package pl.studentmed.facultative.services.user_security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.studentmed.facultative.models.user_info.UserInfo;
import pl.studentmed.facultative.services.user_info.crud.UserInfoCRUDService;

@Service
@RequiredArgsConstructor
public class CurrentUserService implements UserDetailsService {

    private final UserInfoCRUDService userService;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserInfo user = userService.getUserInfoByEmail(username);
        if (user != null)
        {
            final CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername(user.getEmail());
            currentUser.setPassword(user.getPassword());
            return currentUser;
        }
        else
            throw new UsernameNotFoundException("Failed to find user with email: " + username);
    }

}
