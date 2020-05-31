package com.jm.libraryapis.libraryapis.publisher;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceNotFoundException;
import com.jm.libraryapis.libraryapis.util.LibraryApiUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.UUID;
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
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
     {

         if(!LibraryApiUtils.doesStringValueExist(traceId)) {
             traceId = UUID.randomUUID().toString();
         }

         Publisher publisher = null;

        try {
            publisher = publisherService.getPublisher(publisherId, traceId);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
      }

      //  return new Publisher(publisherId, "name1", "aaaa@example.com", "111111111");

        //name: "name1" , emailId: "aaaa@example.com", phoneNumber: "1111111111");

    //}

@PostMapping
public ResponseEntity<?> addPublisher(@Valid @RequestBody Publisher publisher, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) {

    if(!LibraryApiUtils.doesStringValueExist(traceId)) {
        traceId = UUID.randomUUID().toString();
    }

        try {
        //publisher =
        publisherService.addPublisher(publisher, traceId);
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
    public ResponseEntity<?> updatePublisher(@PathVariable Integer publisherId, @Valid @RequestBody Publisher publisher, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        try {
            publisher.setPublisherId(publisherId);
            publisherService.updatePublisher(publisher, traceId);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{publisherId}")
    public ResponseEntity<?> deletePublisher(@PathVariable Integer publisherId, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)  {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        try {
            publisherService.deletePublisher(publisherId, traceId);
        } catch (LibraryResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPublisher(@RequestParam String name, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        if(!LibraryApiUtils.doesStringValueExist(name))  {
            return new ResponseEntity<>( "please enter a name to search Publisher.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(publisherService.searchPublisher(name, traceId), HttpStatus.OK);
    }



}




















