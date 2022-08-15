package com.myblog.controller;

//하 import 안해서 cannot resolved symbol...
import com.myblog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j //롬복을 쓰니
@RestController
public class PostController {

    //Http Method:GET, POST
    @PostMapping("/posts")
    //@RequestParam String title, @RequestParam String content
    //requestBody...바디에 JSON데이터가 넘어옴
    public String post(@RequestBody PostCreate params) {
        log.info("parms={}", params.toString());
        return "Hello world";
    }
}
