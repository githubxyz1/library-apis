package com.jm.libraryapis.libraryapis.publisher;

import com.jm.libraryapis.libraryapis.exception.LibraryResourceAlreadyExistException;
import com.jm.libraryapis.libraryapis.exception.LibraryResourceNotFoundException;
import com.jm.libraryapis.libraryapis.util.LibraryApiUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PublisherService {

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
            throw new LibraryResourceAlreadyExistException("TraceId: " + traceId + "publisher already exists!!");
        }

        publisherToBeAdded.setPublisherId(addedPublisher.getPublisherId());
        // return publisherToBeAdded;
    }

    public Publisher getPublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException {

        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
        Publisher publisher = null;

        if (publisherEntity.isPresent()) {

            PublisherEntity pe = publisherEntity.get();
            publisher = createPublisherFromEntity(pe);
        } else {
            throw new LibraryResourceNotFoundException("TraceId: " + traceId + "Publisher Id: " + publisherId + " Not found");
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
            throw new LibraryResourceNotFoundException("TraceId: " + traceId + "Publisher Id: " + publisherToBeUpdated.getPublisherId() + " Not found");
        }
    }

    public void deletePublisher(Integer publisherId, String traceId) throws LibraryResourceNotFoundException, EmptyResultDataAccessException {

        try {
            publisherRepository.deleteById(publisherId);
        } catch(EmptyResultDataAccessException e) {
            throw new LibraryResourceNotFoundException("TraceId: " + traceId + "Publisher Id: " + publisherId + "Not Found");
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











