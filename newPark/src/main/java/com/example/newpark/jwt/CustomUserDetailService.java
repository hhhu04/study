package com.example.newpark.jwt;

import com.example.newpark.entity.Manager;
import com.example.newpark.repository.ManagerRepositoy;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final ManagerRepositoy managerRepositoy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Manager manager = managerRepositoy.findByManagerId(username);
        if(manager == null){
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(manager.getUsername())
                .password(manager.getPassword())
                .roles(manager.getRoles())
                .build();
    }
}
