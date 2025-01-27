package org.myProject.focus_flow_auth_service_api.api.config;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_auth_service_api.api.exceptions.CustomAppException;
import org.myProject.focus_flow_auth_service_api.store.entities.UserEntity;
import org.myProject.focus_flow_auth_service_api.store.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class UserDetailsServiceImpl implements UserDetailsService{

    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new CustomAppException(
                        HttpStatus.BAD_REQUEST,
                        String.format("User with name %s doesn't exist", username)
                ));

        return UserDetailsImpl.build(user);
    }
}
