package com.example.newpark.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Manager implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String managerId;

    private String password;

    private String name;

    private String grade;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime expireAt;

    private String roles;

    private String token;


    public String passwordEncoder(String password,PasswordEncoder passwordEncoder){
        return passwordEncoder.encode(password);
    }

    public Manager join(Manager manager, PasswordEncoder passwordEncoder){
        manager.setCreatedAt(LocalDateTime.now());
        this.password = passwordEncoder(manager.password,passwordEncoder);
        manager.setGrade("normal");
        manager.setRoles("MANAGER");
        return manager;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("a");
//        roles.add(this.roles);
        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return managerId;
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
