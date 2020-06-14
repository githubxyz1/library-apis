package com.jm.libraryapis.libraryapis.author;

import com.jm.libraryapis.libraryapis.publisher.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Integer> {
    List<AuthorEntity> findByFirstNameContaining(String firstName);

    List<AuthorEntity> findByLastNameContaining(String lastName);

    List<AuthorEntity> findByFirstNameAndLastNameContaining(String firstName, String lastName);
}
