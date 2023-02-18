package microplatform.adservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import microplatform.adservice.domain.Ad;
import microplatform.adservice.domain.IAdService;
import microplatform.adservice.domain.ItemForSale;
import microplatform.adservice.domain.events.ItemForSaleId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

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
        Ad ad = Ad.newAd(
                ItemForSale.builder()
                        .id(ItemForSaleId.create())
                        .name("just a test")
                        .description("just a desc")
                        .build(),
                BigDecimal.valueOf(300.0d),
                "user");
        when(adService.newAd("user", "just a test", "just a desc", BigDecimal.valueOf(300.0d))).thenReturn(ad);

        String json = objectMapper.writeValueAsString(AdDto.of(ad));
        mockMvc.perform(post("/api/ads")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(jwt().authorities(new SimpleGrantedAuthority("SCOPE_write"))))
                .andDo(print())
                .andExpect(status().isOk())
        ;
    }

}
