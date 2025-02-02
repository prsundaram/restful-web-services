package com.psundara.rest.webservices.restful_web_services.controller;

import com.psundara.rest.webservices.restful_web_services.model.Name;
import com.psundara.rest.webservices.restful_web_services.model.PersonV2;
import com.psundara.rest.webservices.restful_web_services.model.PersonV1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bood Charle");
    }

    @GetMapping("v2/person")
    public PersonV2 getSecondVersionVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charle"));
    }

    @GetMapping(path = "person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Bood Charle");
    }

    @GetMapping(path = "person", params = "version=2")
    public PersonV2 getSecondVersionVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charle"));
    }


}
