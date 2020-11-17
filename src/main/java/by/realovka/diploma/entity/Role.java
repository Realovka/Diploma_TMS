package by.realovka.diploma.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Table;


public enum Role implements GrantedAuthority {
    USER, ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}
