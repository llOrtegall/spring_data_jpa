package com.ortega.app.infrastructure.user;

import com.ortega.app.domain.user.User;
import com.ortega.app.domain.user.UserRepository;
import com.ortega.app.infrastructure.mapper.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserEntityRepository implements UserRepository {
    final private CrudUserEntity crudUser;
    final private UserMapper userMapper;

    public UserEntityRepository(CrudUserEntity crudUser, UserMapper userMapper) {
        this.crudUser = crudUser;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = this.userMapper.toEntity(user);
        UserEntity savedEntity = this.crudUser.save(userEntity);
        return this.userMapper.toDomain(savedEntity);
    }

    @Override
    public User findById(String id) {
        UserEntity userEntity = this.crudUser.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
        return this.userMapper.toDomain(userEntity);
    }

    @Override
    public void delete(String id) {
        this.crudUser.deleteById(id);
    }
}
