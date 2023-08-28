package com.minime.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UrlService urlService;

    @Test
    public void shouldReturnEmptyList() throws Exception {
        this.mockMvc.perform(get("/api/v1/urls"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/api/v1/urls/1"))
            .andExpect(status().isNotFound());

        this.mockMvc.perform(
            put("/api/v1/urls/1")
                .content(asJsonString(new Url("https://example.com", "password")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());

        this.mockMvc.perform(delete("/api/v1/urls/1"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnCreatedUrl() throws Exception {
        String url = "https://example.com";
        String password = "secret";
        Url object = new Url(url, password);

        MvcResult mvcResult = this.mockMvc.perform(
            post("/api/v1/urls")
                .content(asJsonString(object))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Url responseUrl = objectMapper.readValue(contentAsString, Url.class);

        assertThat(responseUrl.getUrl()).isEqualTo(url);
        assertThat(responseUrl.getPassword()).isEqualTo(password);

        urlService.deleteUrl(responseUrl.getId());
    }

    @Test
    public void shouldReturnUpdatedUrl() throws Exception {
        Url object = new Url("https://example.com", "secret");
        urlService.createUrl(object);

        String newUrl = "https://google.com";
        String newPassword = "password";

        MvcResult mvcResult = this.mockMvc.perform(
                put("/api/v1/urls/" + object.getId())
                    .content(asJsonString(new Url(newUrl, newPassword)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Url responseUrl = objectMapper.readValue(contentAsString, Url.class);

        assertThat(responseUrl.getUrl()).isEqualTo(newUrl);
        assertThat(responseUrl.getPassword()).isEqualTo(newPassword);

        urlService.deleteUrl(responseUrl.getId());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
