package microplatform.adservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AdControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/api/ads"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllJwt() throws Exception {
        mockMvc.perform(get("/api/ads").with(jwt()))
                .andExpect(status().isOk());
    }

    @Test
    public void createWithJwt() throws Exception {
        String json = objectMapper.writeValueAsString(new AdDto(Long.MAX_VALUE, "just a test"));
        mockMvc.perform(post("/api/ads")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt()
                                .authorities(
                                        new SimpleGrantedAuthority("SCOPE_write"))))
                .andExpect(status().isOk());
    }

    @Test
    public void createJwtForbidden() throws Exception {
        String json = objectMapper.writeValueAsString(new AdDto(Long.MAX_VALUE, "just a test"));
        mockMvc.perform(post("/api/ads")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt()
                                .authorities(
                                        new SimpleGrantedAuthority("SCOPE_read"))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void createUnauthorized() throws Exception {
        String json = objectMapper.writeValueAsString(new AdDto(Long.MAX_VALUE, "just a test"));
        mockMvc.perform(post("/api/ads")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                //todo should be unauthorized
                .andExpect(status().isForbidden());
    }


}
