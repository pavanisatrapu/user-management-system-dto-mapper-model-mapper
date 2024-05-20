package com.myprojects.user_management_system_dto_model_mapper.repository;

import com.myprojects.user_management_system_dto_model_mapper.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
