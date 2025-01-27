package org.myProject.focus_flow_auth_service_api.store.repositories;

import org.myProject.focus_flow_auth_service_api.store.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
}
