package com.propertyservice.property_service.dto.office;

import com.propertyservice.property_service.domain.office.OfficeUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class CustomUserDetails implements UserDetails {

    private final OfficeUser userEntity;

    public CustomUserDetails(OfficeUser userEntity) {
        this.userEntity = userEntity;
    }

    // Role 값 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().name())); // Spring Security 권한 표준에 맞춤

        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPasswordHash();
    }

    // Email 반환
    @Override
    public String getUsername() {
        return userEntity.getEmail();
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
