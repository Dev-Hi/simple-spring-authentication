package com.example.security.practice.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    MockMvc mvc;

    @Test @DisplayName("login url를 GET 요청할 때, 200 OK를 받는다.")
    @WithAnonymousUser
    public void test1() throws Exception {
        mvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test @DisplayName("인증 후, 보호된 endpoint에 접근할 때, 200 OK를 받는다.")
    @WithUserDetails
    public void test2() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isOk());
    }

    @Test @DisplayName("인증을 하지 않은 상태에서 보호된 endpoint에 접근할 때 401 unauthorized를 받는다.")
    public void test3() throws Exception {
        mvc.perform(get("/hello")).andExpect(status().isUnauthorized());
    }

    @Test @DisplayName("사용자명이 user일 때, /admin에 요청하면 403 forbidden를 받는다.")
    @WithUserDetails
    public void test4() throws Exception {
        mvc.perform(get("/admin")).andExpect(status().isForbidden());
    }

    @Test @DisplayName("사용자명이 admin일 때, /admin에 요청하면 200 OK를 받는다.")
    @WithUserDetails(value = "admin")
    public void test5() throws Exception {
        mvc.perform(get("/admin")).andExpect(status().isOk());
    }

}
