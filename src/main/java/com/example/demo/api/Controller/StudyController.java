package com.example.demo.api.Controller;

@RestController
public class StudyController {
    @getMapping("/hello")
    public String sayhello(){
        return "hello";
    }
}
