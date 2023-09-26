package me.hyunseok.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.hyunseok.springbootdeveloper.domain.User;
import me.hyunseok.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
// 스프링 시큐리티에서 사용자 정보를 갖고오는 인터페이스
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    // 원형은 UserDetails를 반환하는 것이며 User 클래스는 UserDetails를 extend하므로 가능.
    @Override
    public User loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }
}
