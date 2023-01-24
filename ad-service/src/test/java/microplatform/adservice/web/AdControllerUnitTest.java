package microplatform.adservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import microplatform.adservice.domain.Ad;
import microplatform.adservice.domain.IAdService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AdController.class)
@AutoConfigureMockMvc
class AdControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private IAdService adService;

    @Test
    public void getIsNotAllowed() throws Exception {
        mockMvc.perform(get("/api/ads")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(
                        status().isUnauthorized())
        ;
    }

    @Test
    public void getIsAllowed() throws Exception {
        mockMvc.perform(get("/api/ads")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt()))
                .andDo(print())
                .andExpect(
                        status().isOk())
                .andExpect(
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        ;
    }

    @Test
    public void postIsUnauthorized() throws Exception {
        mockMvc.perform(post("/api/ads").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden())
        ;
    }

    @Test
    public void postWithAuthorizedUser() throws Exception {
        Ad ad = new Ad("just a test");
        ad.setId(Long.MAX_VALUE);
        when(adService.newAd("user", "just a test", null, null)).thenReturn(ad);

        String json = objectMapper.writeValueAsString(new AdDto(Long.MAX_VALUE, "just a test"));
        mockMvc.perform(post("/api/ads")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_write"))))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}
