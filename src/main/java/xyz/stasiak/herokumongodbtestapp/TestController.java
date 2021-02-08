package xyz.stasiak.herokumongodbtestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        String var = System.getenv("VAR");
        return var != null ? var : "hello";
    }
}
