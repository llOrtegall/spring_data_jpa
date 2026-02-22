package com.ortega.app.domain.user;

public interface UserRepository {
    User save(User user);
    User findById(String id);
    void delete(String id);
}
