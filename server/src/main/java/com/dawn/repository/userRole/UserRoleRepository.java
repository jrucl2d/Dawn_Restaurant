package com.dawn.repository.userRole;

import com.dawn.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>, CustomUserRoleRepository {
}
