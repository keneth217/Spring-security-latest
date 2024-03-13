package com.example.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;

    /**
     * @return 
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * @return 
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * @return 
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * @return 
     */
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    /**
     * @return 
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * @return 
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * @return 
     */
    @Override
    public boolean isEnabled() {
        return false;
    }

}