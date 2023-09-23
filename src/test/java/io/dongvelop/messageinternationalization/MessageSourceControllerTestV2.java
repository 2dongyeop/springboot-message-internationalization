package io.dongvelop.messageinternationalization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageSourceController.class)
class MessageSourceControllerTestV2 {

    @Autowired
    private MockMvc mockMvc;

    /**
     *
     * V2 : HTTP Request Header에 Accept-Language를 지정해서 국제화하는 방식
     *
     */
    @Test
    @DisplayName("Accept-Language: ko-kr, [GET] /say/hello")
    public void sayHelloInKo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/say/hello")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "ko-kr")
                        .header(HttpHeaders.ACCEPT_ENCODING, "UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("안녕!"))
                .andDo(print());
    }

    @Test
    @DisplayName("Accept-Language: en-us, [GET] /say/hello")
    public void sayHelloInEn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/say/hello")
                        .header(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
                        .header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("hi!"))
                .andDo(print());
    }

}
