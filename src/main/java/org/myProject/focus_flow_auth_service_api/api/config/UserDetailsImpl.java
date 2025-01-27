package org.myProject.focus_flow_auth_service_api.api.config;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_auth_service_api.api.dto.UserDetailsDto;
import org.myProject.focus_flow_auth_service_api.store.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Builder
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    UserDetailsDto userDetails;

    public static UserDetailsImpl build(UserEntity user) {
        return UserDetailsImpl
                .builder()
                .userDetails(new UserDetailsDto(user))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(userDetails.getRole());
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
