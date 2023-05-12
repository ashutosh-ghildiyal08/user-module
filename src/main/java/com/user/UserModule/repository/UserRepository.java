package com.user.UserModule.repository;

import com.user.UserModule.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public UserEntity findByEmailAndPassword(String email, String password);
}
