// src/main/java/com/driving/repository/AdminRepository.java
package com.driving.repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    /**
     * 根据用户名查找管理员
     */
    Optional<Admin> findByUsername(String username);

    /**
     * 检查管理员是否存在
     */
    boolean existsByUsername(String username);
}