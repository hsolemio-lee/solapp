package com.sol.solapp.user.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sol.solapp.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @Test
    public void createUsers() throws Exception {
        String content = "id,firstname,lastname,email" +
                "\n1,sol,lee,test1@test.com" +
                "\n2,sol,lee,test2@test.com" +
                "\n3,sol,lee,test3@test.com" +
                "\n4,sol,lee,test4@test.com";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("user-file","test.csv", "text/csv", content.getBytes());

        this.mvc.perform(MockMvcRequestBuilders.multipart("/rest/v1/users/upload")
                .file("file", mockMultipartFile.getBytes()))
                .andExpect(status().isOk());
    }

}
