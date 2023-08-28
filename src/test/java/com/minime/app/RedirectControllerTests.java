package com.minime.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RedirectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UrlService urlService;

    @Test
    public void shouldReturnNotFound() throws Exception {
        // Object doesn't exist
        this.mockMvc.perform(get("/hash12"))
            .andExpect(status().isNotFound());

        // Hash is too short
        this.mockMvc.perform(get("/h"))
            .andExpect(status().isNotFound());

        // Hash is too long
        this.mockMvc.perform(get("/hash12345"))
            .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnRedirect() throws Exception {
        String url = "https://example.com";
        Url testUrl = new Url(url);
        urlService.createUrl(testUrl);
        String hash = testUrl.getHash();
        this.mockMvc.perform(get("/" + hash))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", url));

        urlService.deleteUrl(testUrl.getId());
    }
}
