package com.jm.libraryapis.libraryapis.testutils;

import com.jm.libraryapis.libraryapis.author.Author;
import com.jm.libraryapis.libraryapis.author.AuthorEntity;
import com.jm.libraryapis.libraryapis.model.common.Gender;
import com.jm.libraryapis.libraryapis.publisher.Publisher;
import com.jm.libraryapis.libraryapis.publisher.PublisherEntity;
import com.jm.libraryapis.libraryapis.user.User;
import com.jm.libraryapis.libraryapis.user.UserEntity;

import java.time.LocalDate;
import java.util.Optional;

public class LibraryApiTestUtil {

    public static Publisher createPublisher() {
        return new Publisher(null, TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
    }

    public static PublisherEntity createPublisherEntity() {
        return new PublisherEntity(TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
    }

    public static Optional<PublisherEntity> createPublisherEntityOptional() {
        return Optional.of(createPublisherEntity());
    }

    public static Author createAuthor() {
        return new Author(null, TestConstants.TEST_AUTHOR_FIRST_NAME,
                TestConstants.TEST_AUTHOR_LAST_NAME, LocalDate.now().minusYears(30), Gender.Female);
    }

    public static AuthorEntity createAuthorEntity() {
        return new AuthorEntity(TestConstants.TEST_AUTHOR_FIRST_NAME,
                TestConstants.TEST_AUTHOR_LAST_NAME, LocalDate.now().minusYears(30), Gender.Female);
    }

    public static Optional<AuthorEntity> createAuthorEntityOptional() {
        return Optional.of(createAuthorEntity());
    }

    public static User createUser(String username) {

        return new User(username, TestConstants.TEST_USER_FIRST_NAME,
                TestConstants.TEST_USER_LAST_NAME, LocalDate.now().minusYears(30), Gender.Female, TestConstants.TEST_USER_PHONE,
                username + "@email.com");

    }

    public static UserEntity createUserEntity(String username) {
        UserEntity be = new UserEntity(username, TestConstants.TEST_USER_PASSWORD, TestConstants.TEST_USER_FIRST_NAME,
                TestConstants.TEST_USER_LAST_NAME, LocalDate.now().minusYears(20), TestConstants.TEST_USER_GENDER,
                TestConstants.TEST_USER_PHONE, TestConstants.TEST_USER_EMAIL, "USER");
        return be;
    }

    public static Optional<UserEntity> createUserEntityOptional(String username) {
        return Optional.of(createUserEntity(username));
    }
}
