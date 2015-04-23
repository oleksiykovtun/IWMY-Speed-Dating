package com.oleksiykovtun.iwmy.speeddating.data;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import java.io.Serializable;

/**
 * Created by alx on 2015-02-26.
 */
@Entity
@Cache
@Index
@JsonPropertyOrder(alphabetic=true)
public class Email implements Serializable, Comparable<Email> {

    public static transient final String VALIDATION_REGEX = "^[_A-Za-z0-9-\\+]+" +
            "(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @Id
    private String _emailId;

    private String fromAddress;
    private String fromName;
    private String toAddress;
    private String toName;
    private String subject;
    private String message;
    private String creationTime; // long millis

    public Email() { }

    public Email(String fromAddress, String fromName, String toAddress, String toName,
                 String subject, String message) {
        this.fromAddress = fromAddress;
        this.fromName = fromName;
        this.toAddress = toAddress;
        this.toName = toName;
        this.subject = subject;
        this.message = message;
        this.creationTime = "" + System.currentTimeMillis();
        generateId();
    }

    private void generateId() {
        this._emailId = getCreationTime() + "_" + getToAddress();
    }

    @Override
    public int compareTo(Email other) {
        return this.get_emailId().compareTo(other.get_emailId());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof Email)
                && this.get_emailId().equals(((Email)other).get_emailId());
    }

    public String get_emailId() {
        return _emailId;
    }

    public void set_emailId(String _emailId) {
        this._emailId = "" + _emailId;
    }

    public String getCreationTime() {
        return "" + creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
        generateId();
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
        generateId();
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
