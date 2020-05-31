package com.jm.libraryapis.libraryapis.publisher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity, Integer> {
 // new fix 5/30/2020 spring
   List<PublisherEntity> findByNameContaining(String name);
}
