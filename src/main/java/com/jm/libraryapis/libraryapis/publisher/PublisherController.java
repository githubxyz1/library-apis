package com.jm.libraryapis.libraryapis.publisher;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceNotFoundException;
import com.jm.libraryapis.libraryapis.util.LibraryApiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
//import com.jm.libraryapis.libraryapis.exception;

//import com.jm.libraryapis.libraryapis.exception.exception.ibraryResourceAlreadyExistException;

@RestController
@RequestMapping(path = "/v1/publishers")

public class PublisherController {

    private PublisherService publisherService;
    private Integer publisherId;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherId}")
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId) {
        Publisher publisher = null;

        try {
            publisher = publisherService.getPublisher(publisherId);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
      }

      //  return new Publisher(publisherId, "name1", "aaaa@example.com", "111111111");

        //name: "name1" , emailId: "aaaa@example.com", phoneNumber: "1111111111");

    //}

@PostMapping
public ResponseEntity<?> addPublisher(@RequestBody Publisher publisher) {
    try {
        //publisher =
        publisherService.addPublisher(publisher);
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

    @PutMapping(path = "/{publisherId}")
    public ResponseEntity<?> updatePublisher(@PathVariable Integer publisherId, @RequestBody Publisher publisher) {

        try {
            publisher.setPublisherId(publisherId);
            publisherService.updatePublisher(publisher);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{publisherId}")
    public ResponseEntity<?> deletePublisher(@PathVariable Integer publisherId)  {

        try {
            publisherService.deletePublisher(publisherId);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPublisher(@RequestParam String name) {

        if(!LibraryApiUtils.doesStringValueExist(name))  {
            return new ResponseEntity<>( "please enter a name to search Publisher.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(publisherService.searchPublisher(name), HttpStatus.OK);
    }



}




















