package com.jm.libraryapis.libraryapis.publisher;

import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceNotFoundException;
import com.jm.libraryapis.libraryapis.util.LibraryApiUtils;
//import org.slf4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

//import static org.slf4j.Logger.*;

//import static org.slf4j.Logger.*;
//import static org.slf4j.LoggerFactory.*;

@Service
public class PublisherService {

    private static Logger logger = LoggerFactory.getLogger(PublisherService.class);
    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public void addPublisher(Publisher publisherToBeAdded, @RequestHeader(value = "Trace-Id", defaultValue = "") String traceId)
            throws LibraryResourceAlreadyExistException {

        if(!LibraryApiUtils.doesStringValueExist(traceId)) {
            traceId = UUID.randomUUID().toString();
        }


        PublisherEntity publisherEntity = new PublisherEntity(
                publisherToBeAdded.getName(),
                publisherToBeAdded.getEmailId(),
                publisherToBeAdded.getPhoneNumber()
        );

        PublisherEntity addedPublisher = null;

        try {
            addedPublisher = publisherRepository.save(publisherEntity);
        } catch (DataIntegrityViolationException e) {
            logger.error("TraceId: {}, Publisher already exists!!", traceId, e);
            throw new LibraryResourceAlreadyExistException(traceId, "Publisher already exists!!");
        }
        logger.info("TraceId : {}, before Publisher added: {}, publisherId in PublisherService: {}", traceId, publisherToBeAdded, addedPublisher.getPublisherId());
//here1x !!!???to fix unit test issue        publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
        logger.info("TraceId : {}, Publisher added: {}, publisherId in PublisherService: {}", traceId, publisherToBeAdded, publisherToBeAdded.getPublisherId());
        // return publisherToBeAdded;
    }

    public Publisher getPublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {

        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
        //??!! here1x Publisher publisher = null;
        logger.info("inside PublisherService - publisherId = : {}" , publisherId);
        logger.info("publisherEntity = : {} " , publisherEntity);
        //!! causes error logger.info("publisherEntity.get() = : {}", publisherEntity.get());
        Publisher publisher;

        if (publisherEntity.isPresent()) {

            PublisherEntity pe = publisherEntity.get();
            logger.info("pe.getPublisherId() = : {}" , pe.getPublisherId());
            publisher = createPublisherFromEntity(pe);
            logger.info("publisherId inside PublisherService.getPublisher = : {} , publisherId = : {}" , publisher.getPublisherId() , publisherId);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherId + " Not found");
        }
        return publisher;
    }

    private Publisher createPublisherFromEntity(PublisherEntity pe) {
        return new Publisher(pe.getPublisherId(), pe.getName(), pe.getEmailId(), pe.getPhoneNumber());
    }

    public void updatePublisher(Publisher publisherToBeUpdated, String traceId) throws LibraryResourceNotFoundException {

        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherToBeUpdated.getPublisherId());
        Publisher publisher = null;

        if (publisherEntity.isPresent()) {

            PublisherEntity pe = publisherEntity.get();
            if (LibraryApiUtils.doesStringValueExist(publisherToBeUpdated.getEmailId())) {
                pe.setEmailId(publisherToBeUpdated.getEmailId());
            }
            if (LibraryApiUtils.doesStringValueExist(publisherToBeUpdated.getPhoneNumber())) {
                pe.setPhoneNumber(publisherToBeUpdated.getPhoneNumber());
            }
            publisherRepository.save(pe);
            publisherToBeUpdated = createPublisherFromEntity(pe);
        } else {
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherToBeUpdated.getPublisherId() + " Not found");
        }
    }

    public void deletePublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException, EmptyResultDataAccessException {

        try {
            publisherRepository.deleteById(publisherId);
        } catch(EmptyResultDataAccessException e) {
            logger.error("TraceId: {}, Publisher Not Found!", traceId, e);
            throw new LibraryResourceNotFoundException(traceId, "Publisher Id: " + publisherId + "Not Found");
        }
    }

    public List<Publisher> searchPublisher(String name, String traceId) {
        List<PublisherEntity> publisherEntities = null;
        if(LibraryApiUtils.doesStringValueExist(name)) {
            publisherEntities = publisherRepository.findByNameContaining(name);
        }
        if(publisherEntities != null && publisherEntities.size() > 0) {
            return createPublishersForSearchResponse(publisherEntities);
        } else {
            return Collections.emptyList();
            
        }
    }

    private List<Publisher> createPublishersForSearchResponse(List<PublisherEntity> publisherEntities) {

        return publisherEntities.stream()
                .map(pe -> createPublisherFromEntity(pe))
                .collect(Collectors.toList());
    }
}











