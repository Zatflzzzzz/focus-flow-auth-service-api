package org.myProject.focus_flow_auth_service_api.store.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Language;
import org.myProject.focus_flow_auth_service_api.store.entities.enums.Themes;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "settings")
public class SettingEntity {

    boolean notification;

    Themes theme;

    Language language;
}
