package xyz.stasiak.herokumongodbtestapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TestController {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Value("${testVar}")
    private String envVar;

    public TestController(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/hello")
    public String hello() {
        String var = System.getenv("VAR");
        return var != null ? var : "hello";
    }

    @GetMapping("/envvar")
    public String getEnvVar() {
        return envVar;
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer toSave) {
        Customer result = customerRepository.save(toSave);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerRepository.findById(id)
                                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer/{firstName}")
    public ResponseEntity<Customer> getCustomerByFirstName(@PathVariable String firstName) {
        Customer customer = customerRepository.findByFirstName(firstName)
                                              .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @PostMapping("/address")
    public ResponseEntity<Address> saveAddress(@RequestBody Address toSave) {
        Address result = addressRepository.save(toSave);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable String id) {
        Address customer = addressRepository.findById(id)
                                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressRepository.findAll());
    }
}
