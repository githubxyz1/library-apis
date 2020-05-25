package com.jm.libraryapis.libraryapis.publisher;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
//import com.jm.libraryapis.libraryapis.exception;

//import com.jm.libraryapis.libraryapis.exception.exception.ibraryResourceAlreadyExistException;

@RestController
@RequestMapping(path = "/v1/publishers")

public class PublisherController {

    private PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable Integer publisherId) {
        return new Publisher(publisherId, "name1", "aaaa@example.com", "111111111");

        //name: "name1" , emailId: "aaaa@example.com", phoneNumber: "1111111111");

    }

@PostMapping
public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher) {
    try {
        publisher = publisherService.addPublisher(publisher);
    } catch (LibraryResourceAlreadyExistException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    return new ResponseEntity<>(publisher, HttpStatus.CREATED);
}

 //   try {
//        publisher = publisherService.addPublisher(publisher);
 //   } catch (LibraryResourceAlreadyExistException e) {
   //     return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    //}

  //  return new ResponseEntity<>(publisher, HttpStatus.CREATED);
   //}
//}

}




















