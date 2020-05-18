package com.jm.libraryapis.libraryapis.publisher.controller;

import com.jm.libraryapis.libraryapis.publisher.model.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/publishers")

public class PublisherController {
    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable String publisherId) {
        return new Publisher(publisherId, "name1", "aaaa@example.com", "111111111");

                //name: "name1" , emailId: "aaaa@example.com", phoneNumber: "1111111111");

    }
}
