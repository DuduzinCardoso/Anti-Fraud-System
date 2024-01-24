package antifraud.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class UserEntityDetails implements org.springframework.security.core.userdetails.UserDetails {
    private final String username;
    private final String password;
    private final boolean isAccountNonLocked;
    private final List<GrantedAuthority> grantedAuthorities;

    public UserEntityDetails(UserEntity userEntity) {
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.isAccountNonLocked = userEntity.isAccountNonLocked();
        this.grantedAuthorities = List.of(new SimpleGrantedAuthority(userEntity.getRole().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
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
        return isAccountNonLocked;
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
