package com.jm.libraryapis.libraryapis.publisher;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceBadRequestException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceNotFoundException;
import com.jm.libraryapis.libraryapis.util.LibraryApiUtils;
import jdk.nashorn.internal.lookup.MethodHandleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger logger = LoggerFactory.getLogger(PublisherController.class);

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping(path = "/{publisherId}")
    public ResponseEntity<?> getPublisher(@PathVariable Integer publisherId,
                                          @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

         if(!LibraryApiUtils.doesStringValueExist(traceId)) {
             traceId = UUID.randomUUID().toString();
         }

        return new ResponseEntity<>(publisherService.getPublisher(publisherId, traceId), HttpStatus.OK);

    }

      //  return new Publisher(publisherId, "name1", "aaaa@example.com", "111111111");

        //name: "name1" , emailId: "aaaa@example.com", phoneNumber: "1111111111");

    //}

@PostMapping
public ResponseEntity<?> addPublisher(@Valid @RequestBody Publisher publisher,
                                      @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
        throws LibraryResourceAlreadyExistException {

    logger.debug("Request to add Publisher: {}", publisher);
    if(!LibraryApiUtils.doesStringValueExist(traceId)) {
        traceId = UUID.randomUUID().toString();
    }
    logger.debug("Added TraceId: {}", traceId);
    publisherService.addPublisher(publisher, traceId);
    logger.debug("Returning response for TraceId: {}", traceId);
    return new ResponseEntity<>(publisher, HttpStatus.CREATED);
}

    @PutMapping(path = "/{publisherId}")
    public ResponseEntity<?> updatePublisher(@PathVariable Integer publisherId, @Valid @RequestBody Publisher publisher, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId) throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        publisher.setPublisherId(publisherId);
        publisherService.updatePublisher(publisher, traceId);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{publisherId}")
    public ResponseEntity<?> deletePublisher(@PathVariable Integer publisherId, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceNotFoundException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        publisherService.deletePublisher(publisherId, traceId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity<?> searchPublisher(@RequestParam String name,
                                             @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceBadRequestException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }

        if(!LibraryApiUtils.doesStringValueExist(name))  {
            throw new LibraryResourceBadRequestException(traceId, "Please enter a name to search.");
        }
        return new ResponseEntity<>(publisherService.searchPublisher(name, traceId), HttpStatus.OK);
    }



}




















