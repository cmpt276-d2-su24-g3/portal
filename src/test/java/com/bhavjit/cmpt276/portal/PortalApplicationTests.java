package com.bhavjit.cmpt276.portal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.bhavjit.cmpt276.portal.models.User;

@SpringBootTest
@AutoConfigureMockMvc
class PortalApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRedirectToLogin() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldNotRedirectIfLoggedIn() throws Exception {
        this.mockMvc.perform(get("/").sessionAttr("user", new User())).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnRegisterPage() throws Exception {
        this.mockMvc.perform(get("/register")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void shouldRedirectRegisterIfLoggedIn() throws Exception {
        this.mockMvc.perform(get("/register").sessionAttr("user", new User())).andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldRedirectLoginIfLoggedIn() throws Exception {
        this.mockMvc.perform(get("/login").sessionAttr("user", new User())).andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void shouldRedirectLogout() throws Exception {
        this.mockMvc.perform(get("/logout").sessionAttr("user", new User())).andDo(print()).andExpect(status().is3xxRedirection());
    }

}
