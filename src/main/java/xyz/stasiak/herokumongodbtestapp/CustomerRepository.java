package xyz.stasiak.herokumongodbtestapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByFirstName(String firstName);

    @Query(sort = "{ rating: 1 }")
    List<Customer> findByRatingBetween(int from, int to);
}
