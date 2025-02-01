package com.psundara.rest.webservices.restful_web_services.controller;

import com.psundara.rest.webservices.restful_web_services.controller.model.HelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWord() {
        return "Hello World";
    }

    @GetMapping("hello-world-bean")
    public HelloWorld helloWordBean() {
        return new HelloWorld("Hello World");
    }

    @GetMapping("hello-world/path-variable/{name}")
    public HelloWorld helloWordBean(@PathVariable String name) {
        return new HelloWorld(String.format("Hello World, %s", name));
    }
}
