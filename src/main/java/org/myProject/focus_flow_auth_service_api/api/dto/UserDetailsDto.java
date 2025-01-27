package org.myProject.focus_flow_auth_service_api.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_auth_service_api.store.entities.UserEntity;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserDetailsDto {

    @NonNull
    Long id;

    @NonNull
    String email;

    @NonNull
    String username;

    @NonNull
    String password;

    @NonNull
    Role role;

    public UserDetailsDto(UserEntity user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
