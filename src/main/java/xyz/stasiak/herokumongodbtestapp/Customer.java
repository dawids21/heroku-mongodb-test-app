package xyz.stasiak.herokumongodbtestapp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private int rating;
    private Set<CreditCard> creditCards;

    public Customer() {
    }

    public Customer(String firstName, String lastName, int rating, Set<CreditCard> creditCards) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rating = rating;
        this.creditCards = creditCards;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {
        this.creditCards = creditCards;
    }

    @Override
    public String toString() {
        return "Customer{" + "id='" + id + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName +
               '\'' + ", rating=" + rating + ", creditCards=" + creditCards + '}';
    }
}
