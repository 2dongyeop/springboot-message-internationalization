package io.dongvelop.messageinternationalization;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageSourceController.class)
class MessageSourceControllerTestV1 {

    @Autowired
    private MockMvc mockMvc;

    /**
     *
     *  V1 : lang을 파라미터로 받아 국제화 처리를 하는 방식
     *
     */
    @Test
    @DisplayName("[GET] /say/hello?lang=ko-kr")
    public void sayHelloInKo() throws Exception {
        mockMvc.perform(get("/say/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("lang", "ko-kr"))
                .andExpect(status().isOk())
                .andExpect(content().string("안녕!"))
                .andDo(print());
    }

    @Test
    @DisplayName("[GET] /say/hello?lang=en-us")
    public void sayHelloInEn() throws Exception {
        mockMvc.perform(get("/say/hello")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("lang", "en-us"))
                .andExpect(status().isOk())
                .andExpect(content().string("hi!"))
                .andDo(print());
    }

}