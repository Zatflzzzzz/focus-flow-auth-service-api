package org.myProject.focus_flow_auth_service_api.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Language;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Role;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Status;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Themes;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString
@Table(name = "user")
public class UserEntity {

    @Id
    Long id;

    String email;

    String password;

    String username;

    String telegramLink;

    String profilePicture;

    Status status;

    @Builder.Default
    int numberOfViolations = 0;

    Role role;

    @Builder.Default
    LocalDateTime lastAction = LocalDateTime.now();

    @Builder.Default
    LocalDateTime createdAt = LocalDateTime.now();

    @MappedCollection(idColumn = "user_id")
    @Builder.Default
    SettingEntity settingEntity = new SettingEntity(false, Themes.LIGHT, Language.EN);
}
