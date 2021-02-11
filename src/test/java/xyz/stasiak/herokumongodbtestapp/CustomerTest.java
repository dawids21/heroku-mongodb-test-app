package xyz.stasiak.herokumongodbtestapp;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CustomerTest {

    @Test
    void some_test() {
        var customer = new Customer("Dawid", "Stasiak", 8);

        assertThat(customer.getFirstName()).isEqualTo("Dawid");
    }
}