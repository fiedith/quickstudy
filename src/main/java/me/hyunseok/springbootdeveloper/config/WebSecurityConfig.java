package me.hyunseok.springbootdeveloper.config;

import lombok.RequiredArgsConstructor;
import me.hyunseok.springbootdeveloper.service.UserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    // 1. 일부 경로에 대해 스프링 시큐리티 기능 비활성화
    // 데이터베이스 조회하는 h2-console 하위 url과 정적 파일은 시큐리티 적용이 필요 없으므로 ignoring() 시킨다.
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    // 2. 특정 HTTP 요청에 대한 웹기반 보안 구성 (인증 인가, 로그인 로그아웃 관련 설정)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .authorizeRequests()    // 3. 인증, 인가 설정
                // /login, /signup, /user 에 대해서는 누구나 접근 가능하게 설정(.permitAll())
                .requestMatchers("/login", "/signup", "/user").permitAll()
                // 그 외의 요청에 대해서는 인증이 돼야 접근 가능
                .anyRequest().authenticated()
                .and()
                .formLogin()    // 4. form 기반 로그인 설정
                .loginPage("/login")    // 로그인 경로
                .defaultSuccessUrl("/articles") // 로그인 완료시 이동할 경로
                .and()
                .logout()   // 5. 로그아웃 설정
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .and()
                .csrf().disable()   // 6. csrf 비활성화 (실습용, 실제로는 비활성화 X)
                .build();
    }

    // 7. 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailService userDetailService) throws Exception{

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)    // 8. 사용자 정보 서비스 설정
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 9. password encoder bean
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
