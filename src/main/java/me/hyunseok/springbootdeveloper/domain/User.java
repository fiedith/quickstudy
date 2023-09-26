package me.hyunseok.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    // list of authority types (in this case: only user)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }


    @Override
    public String getPassword() {
        return password;
    }

    // unique name per user such as email
    @Override
    public String getUsername() {
        return email;
    }

    // true: not expired
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // true: not locked
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부. true: not expired
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부. true: enabled
    @Override
    public boolean isEnabled() {
        return true;
    }
}
