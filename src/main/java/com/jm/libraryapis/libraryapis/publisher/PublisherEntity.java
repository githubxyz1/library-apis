package com.jm.libraryapis.libraryapis.publisher;


import com.fasterxml.jackson.annotation.JsonTypeId;
import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.*;

//@GeneratedValue(strategy=GenerationType.AUTO)

@Entity
@Table(name = "PUBLISHER")

public class PublisherEntity {



    @Column(name = "Publisher_Id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherId_generator")
    @SequenceGenerator(name = "publisherId_generator", sequenceName = "publisher_sequence", allocationSize = 50)
    //@Id
    //@GeneratedValue(generator="system-uuid")
    //@GenericGenerator(name="system-uuid", strategy = "uuid")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="id",   unique=true, nullable=false) public Long getId() { return this.id; }

    //@Id
    //@GeneratedValue(strategy=GenerationType.AUTO)

    private Integer publisherId;

    @Column(name = "name")
    private String name;

    @Column(name = "Email_Id")
    private String emailId;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    public PublisherEntity() {
    }

    public PublisherEntity(String name, String emailId, String phoneNumber) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
