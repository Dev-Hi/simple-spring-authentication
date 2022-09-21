package com.example.security.practice.account.jwt;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class TokenProviderTest {

    @InjectMocks
    TokenProvider tokenProvider;
    Authentication authentication;

    @BeforeEach
    public void init() {
        tokenProvider.setTokenLifeTime(86400000L);
        tokenProvider.setSecret("aS1sb3ZlLXByb2dyYW1pbmctYnV0LWktZG9udC1saWtlLXNob3BwaW5naS1sb3ZlLXByb2dyYW1pbmctYnV0LWktZG9udC1saWtlLXNob3BwaW5naS1sb3ZlLXByb2dyYW1pbmctYnV0LWktZG9udC1saWtlLXNob3BwaW5n");
        tokenProvider.setKey(Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(tokenProvider.getSecret())));
        authentication = new TestAuthentication();
    }

    @Test
    @DisplayName("생성된 token을 출력을 통해 확인하는 테스트")
    public void test1() {
        System.out.println(tokenProvider.createToken(authentication));
    }

    @Test
    @DisplayName("token을 받았을 때, token에 대한 권한은 USER임을 확인하는 테스트")
    public void test2() {
        String token = tokenProvider.createToken(authentication);
        Authentication authentication = tokenProvider.getAuthentication(token);
        assertThat(authentication.getAuthorities().size()).isEqualTo(1);
        authentication.getAuthorities().forEach(obj -> assertThat(obj.getAuthority()).isEqualTo("USER"));
        assertThat(authentication.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("유효한 token을 받았을 때, tokenProvider가 해당 token을 유효하다고 판단해야한다.")
    public void test3() {
        String token = tokenProvider.createToken(authentication);
        assertThat(tokenProvider.validateToken(token)).isEqualTo(true);
    }

    @Test
    @DisplayName("token이 null일 때, tokenProvider는 해당 token이 유효하지 않다고 판단해야한다.")
    public void test4() {
        assertThat(tokenProvider.validateToken(null)).isEqualTo(false);
    }

    @Test
    @DisplayName("token이 유효하지 않을 때, tokenProvider는 해당 token이 유효하지 않다고 판단해야한다.")
    public void test5() {
        String token = "12341234.abcd.kkee";
        assertThat(tokenProvider.validateToken(token)).isEqualTo(false);
    }

    private static class TestAuthentication implements Authentication {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(new SimpleGrantedAuthority("USER"));
        }

        @Override
        public Object getCredentials() {
            return null;
        }

        @Override
        public Object getDetails() {
            return null;
        }

        @Override
        public Object getPrincipal() {
            return null;
        }

        @Override
        public boolean isAuthenticated() {
            return false;
        }

        @Override
        public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

        }

        @Override
        public String getName() {
            return "test";
        }

    }

}
