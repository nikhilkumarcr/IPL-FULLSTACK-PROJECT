package com.example.repository;

import com.example.entity.Role;
import com.example.entity.Role_Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Role_Type name);
}
