package com.codecool.nerdSanctuary.controller;

import com.codecool.nerdSanctuary.model.Developer;
import com.codecool.nerdSanctuary.model.Game;
import com.codecool.nerdSanctuary.repository.DeveloperRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeveloperControllerTest {

    private MockMvc mockMvc = null;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private DeveloperRepository developerRepository;

    @Before
    public void before() throws Exception {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void after() throws Exception {
        mockMvc = null;
    }

    @Test
    public void testDevelopersFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDeveloperFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer/9");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDeveloperNotFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer/50");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void testDevelopersGamesFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer/9/games");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDevelopersGamesNotFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer/50/games");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isNotFound());
    }

    @Test
    public void testDeveloperGameFound() throws Exception {
        MockHttpServletRequestBuilder builder = get("/developer/9/games/14");
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDeveloperCreated() throws Exception {
        Developer developer = new Developer(20, "name", "country");
        MockHttpServletRequestBuilder builder = post("/developer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(developer));
        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void testDeveloperUpdateIsOK() throws Exception {
        Developer developer = new Developer(20, "name", "country");

        MockHttpServletRequestBuilder builder = put("/developer/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(developer));

        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testDeveloperUpdateFail() throws Exception {
        Developer developer = new Developer(20, "name", "country");

        MockHttpServletRequestBuilder builder = put("/developer/30")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(developer));

        ResultActions resultActions = mockMvc.perform(builder);
        resultActions.andExpect(status().isNotFound());
    }




}