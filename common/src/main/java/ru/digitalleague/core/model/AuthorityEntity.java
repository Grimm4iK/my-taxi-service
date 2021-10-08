package ru.digitalleague.core.model;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;

@Data
public class AuthorityEntity implements GrantedAuthority {
    private Long id;

    private Long userAccountId;

    private String authority;
}
