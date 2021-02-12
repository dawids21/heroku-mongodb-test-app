package xyz.stasiak.herokumongodbtestapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Addresses")
public class Address {

    @Id
    private String id;

    private String customerId;

    private String city;

    public Address() {
    }

    public Address(String customerId, String city) {
        this.customerId = customerId;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
