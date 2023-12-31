package com.example.choosing.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.choosing.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static com.example.choosing.utils.TestUtil.userHttpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The type Abstract controller test.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
abstract public class AbstractControllerTest {
    /**
     * The constant REST_URL.
     */
    public static final String REST_URL = "/api";

    protected static final LocalDate LOCAL_DATE = LocalDate.now();


    /**
     * The Mock mvc.
     */
    @Autowired
    protected MockMvc mockMvc;

    /**
     * The Object mapper.
     */
    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * GET
     *
     * @param url      the url
     * @param authUser the auth user
     * @throws Exception the exception
     */
    ResultActions testGetAll(String url, User authUser) throws Exception {
        return this.mockMvc.perform(get(url)
                .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get by id result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testGetById(String url, User authUser) throws Exception {
        return this.mockMvc
                .perform(get(url)
                        .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test get is forbidden result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @throws Exception the exception
     */
    ResultActions testGetIsForbidden(String url, User authUser) throws Exception {
        return this.mockMvc
                .perform(get(url)
                        .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    /**
     * Test get is not found result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testGetIsNotFound(String url, User authUser) throws Exception {
        return this.mockMvc
                .perform(get(url)
                        .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    /**
     * Create
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testCreate(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(post(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    /**
     * Test create is conflict result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testCreateIsConflict(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(post(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    /**
     * Test create is forbidden result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testCreateIsForbidden(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(post(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isForbidden());
    }


    /**
     * UPDATE
     * with patch (put issue https://jira.spring.io/projects/DATAREST/issues/DATAREST-1241?filter=allopenissues)
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testUpdate(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(patch(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test update is conflict result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testUpdateIsConflict(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(patch(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isConflict());
    }

    /**
     * Test update is forbidden result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @param body     the body
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testUpdateIsForbidden(String url, User authUser, String body) throws Exception {
        return mockMvc.perform(patch(url)
                .with(userHttpBasic(authUser))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(body))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    /**
     * DELETE
     *
     * @param url      the url
     * @param authUser the auth user
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testDelete(String url, User authUser) throws Exception {
        return mockMvc.perform(delete(url)
                .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    /**
     * Test delete is forbidden result actions.
     *
     * @param url      the url
     * @param authUser the auth user
     * @return the result actions
     * @throws Exception the exception
     */
    ResultActions testDeleteIsForbidden(String url, User authUser) throws Exception {
        return mockMvc.perform(delete(url)
                .with(userHttpBasic(authUser)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    /**
     * Gets all.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void getAll() throws Exception;

    /**
     * Gets by id.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void getById() throws Exception;

    /**
     * Gets is not found.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void getIsNotFound() throws Exception;

    /**
     * Create.
     *
     * @throws Exception the exception
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    abstract void create() throws Exception;

    /**
     * Create is conflict.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void createIsConflict() throws Exception;

    /**
     * Create is forbidden.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void createIsForbidden() throws Exception;

    /**
     * Update.
     *
     * @throws Exception the exception
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    abstract void update() throws Exception;

    /**
     * Updated is conflict.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void updatedIsConflict() throws Exception;

    /**
     * Update is forbidden.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void updateIsForbidden() throws Exception;

    /**
     * Deleted.
     *
     * @throws Exception the exception
     */
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    abstract void deleted() throws Exception;

    /**
     * Deleted is forbidden.
     *
     * @throws Exception the exception
     */
    @Test
    abstract void deletedIsForbidden() throws Exception;
}
