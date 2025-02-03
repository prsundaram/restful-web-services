package com.psundara.rest.webservices.controller;

import com.psundara.rest.webservices.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    private final MessageSource messageSource;

    @Autowired
    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping("hello-world-internationalized")
    public String helloWordInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
