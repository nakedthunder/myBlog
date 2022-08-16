package com.myblog.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest //NullPointException 에러가 남 bean이 없어서 주입함
class PostControllerTest {
    //controller에 요청을 날림
    @Autowired
    private MockMvc mockMvc;

    //cmd + shift + T 를 눌러서 테스트를 만듬 같은 루트안에 테스트를 만듬
    @Test
    @DisplayName("/posts 요청시 Hello World를 출력한다.")
    void test() throws Exception {
        //JSON 형태로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                //JSON도 content-Type 기본값을 지정해줘야함 : 안하면 415에러 떨어짐
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"제목입니다.\", \"content\": \"내용 입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("{}"))
                .andDo(MockMvcResultHandlers.print()); //Http요청에 대한 써머리를 남겨줌
    }
    //데이터 검증
    @Test
    @DisplayName("/posts요청시 title값은 필수다.")
    void test2() throws Exception {
        //JSON 형태로 보냄
        mockMvc.perform(MockMvcRequestBuilders.post("/posts")
                        //JSON도 content-Type 기본값을 지정해줘야함 : 안하면 415에러 떨어짐
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": null, \"content\": \"내용 입니다.\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").value("타이틀을 입력해주세요."))
               // .andExpect(MockMvcResultMatchers.content().string("Hello world"))
                .andDo(MockMvcResultHandlers.print()); //Http요청에 대한 써머리를 남겨줌
    }
}