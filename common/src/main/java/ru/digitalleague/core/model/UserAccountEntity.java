package ru.digitalleague.core.model;

import lombok.Data;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

@Data
public class UserAccountEntity implements UserDetails {
    private Long id;

    private String login;

    private String password;

    private List<AuthorityEntity> authorities;

    @Override
    public String getUsername() {
        return login;
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
