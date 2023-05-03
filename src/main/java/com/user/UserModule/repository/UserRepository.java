package com.user.UserModule.repository;

import com.user.UserModule.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmailAndPassword(String email, String password);
}
