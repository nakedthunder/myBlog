package com.myblog.controller;

//하 import 안해서 cannot resolved symbol...
import com.myblog.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j //롬복을 쓰니
@RestController
public class PostController {

    //Http Method:GET, POST
    @PostMapping("/posts")
    //데이터 검증하는 이유
    //- human에러, client bug, 외부공격, 생각보다 꼼꼼하게 해야함...

    //@RequestParam String title, @RequestParam String content
    //requestBody...바디에 JSON데이터가 넘어옴
    public Map<String, String> post(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        log.info("parms={}", params.toString());
        if(result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            var fieldName = firstFieldError.getField(); //title
            var errorMessage = firstFieldError.getDefaultMessage(); //에러메세지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;

        }
        return Map.of();
    }
}
