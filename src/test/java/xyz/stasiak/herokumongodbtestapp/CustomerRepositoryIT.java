package xyz.stasiak.herokumongodbtestapp;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@Testcontainers
@ActiveProfiles("integration")
class CustomerRepositoryIT {

    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo");

    @DynamicPropertySource
    static void mongoDbProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUpAll() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void tearDownAll() {
        if (!mongoDBContainer.isShouldBeReused()) {
            mongoDBContainer.stop();
        }
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void should_get_customers_with_rating_between_given_values() {
        customerRepository.save(new Customer("Dawid", "Stasiak", 4, Set.of()));
        customerRepository.save(new Customer("Jan", "Nowak", 3, Set.of()));
        customerRepository.save(new Customer("Jan", "Kowalski", 6, Set.of()));

        List<Customer> customers = customerRepository.findByRatingBetween(3, 7);

        assertThat(customers).allMatch(customer -> customer.getRating() > 3 && customer.getRating() < 7);
    }

    @Test
    void should_find_customer_with_given_name() {
        customerRepository.save(new Customer("Dawid", "Stasiak", 4, Set.of()));
        customerRepository.save(new Customer("Jan", "Nowak", 1, Set.of()));
        customerRepository.save(new Customer("Jan", "Kowalski", 6, Set.of()));

        Optional<Customer> customer = customerRepository.findByFirstName("Dawid");

        assertThat(customer.isPresent()).isTrue();
    }
}