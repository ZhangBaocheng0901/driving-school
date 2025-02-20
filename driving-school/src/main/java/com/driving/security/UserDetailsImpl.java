// src/main/java/com/driving/security/UserDetailsImpl.java
package com.driving.security;

import com.driving.entity.Admin;
import com.driving.entity.Coach;
import com.driving.entity.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private final Long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Student student) {
        this.id = student.getId();
        this.username = student.getUsername();
        this.password = student.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("STUDENT"));
    }

    public UserDetailsImpl(Coach coach) {
        this.id = coach.getId();
        this.username = coach.getUsername();
        this.password = coach.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("COACH"));
    }

    public UserDetailsImpl(Admin admin) {
        this.id = admin.getId();
        this.username = admin.getUsername();
        this.password = admin.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}