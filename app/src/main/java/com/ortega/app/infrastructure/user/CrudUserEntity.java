package com.ortega.app.infrastructure.user;

import org.springframework.data.repository.CrudRepository;

public interface CrudUserEntity extends CrudRepository<UserEntity, String> {
}
