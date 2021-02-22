package com.heeyoung.springboot.config.auth;


import com.heeyoung.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.CustomUserTypesOAuth2UserService;

@RequiredArgsConstructor
@EnableWebSecurity //spring security설정을 활성
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //h2-console화면을 사용하기 위해 해당 옵션들을 disable 한다
        http.csrf().disable().headers().frameOptions().disable();

        //URL별 권한 관리를 설정하는 옵션의 시작점. 이걸 선언해야 앤트매쳐 쓸 수 있음
        http.authorizeRequests()
                .antMatchers("/","/css/**","/images/**","/js/**", "/h2-console/**","/profile").permitAll() // 권한관리 대상을 지정하는 옵션. URL, HTTP메소드별로 관리 가능 permitAll로 전체 열람권한 주기
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //api/v1/어쩌구 API는 USER만 가능
                .anyRequest().authenticated(); //설정된 값들 이외 나머지 URL ..인증된 사용자만 허용

        //로그아웃 시 설정
        http.logout()
                .logoutSuccessUrl("/");

        //OAuth2로그인 기능에 대한 설정
        http.oauth2Login()
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                .userService(customOAuth2UserService); //소셜 로그인 성공 시 후속 조치를 진행할 유저서비스 인터페이스의 구현체를 등록
        // 리로스서버(소셜서버들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
    }
}
