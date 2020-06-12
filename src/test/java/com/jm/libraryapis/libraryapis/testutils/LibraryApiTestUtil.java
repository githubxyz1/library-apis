package com.jm.libraryapis.libraryapis.testutils;

import com.jm.libraryapis.libraryapis.publisher.Publisher;
import com.jm.libraryapis.libraryapis.publisher.PublisherEntity;
import com.jm.libraryapis.libraryapis.publisher.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static com.jm.libraryapis.libraryapis.testutils.TestConstants.*;


public class LibraryApiTestUtil {

    private static Logger logger = LoggerFactory.getLogger(PublisherService.class);

    public static Publisher createPublisher() {
        return new Publisher( null, TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
        }

    public static Publisher createPublisher1() {

        return new Publisher(111, TestConstants.TEST_PUBLISHER_NAME,
                TestConstants.TEST_PUBLISHER_EMAIL,
                TestConstants.TEST_PUBLISHER_PHONE);
    }



    //public static PublisherEntity createPublisherEntity() {
    //    return new PublisherEntity(TEST_PUBLISHER_NAME,
    //                TEST_PUBLISHER_EMAIL,
    //                TEST_PUBLISHER_PHONE);
    //}

    public static PublisherEntity createPublisherEntity() {
        logger.info("inside createPublisherEntity");
        return new PublisherEntity(TEST_PUBLISHER_NAME,
                TEST_PUBLISHER_EMAIL,
                TEST_PUBLISHER_PHONE);
    }

    public static Optional<PublisherEntity> createPublisherEntity1Optional() {
        return Optional.of(createPublisherEntity());
    }

    public static Optional<PublisherEntity> createPublisherEntityOptional() {
        return Optional.of(createPublisherEntity());
    }
}
