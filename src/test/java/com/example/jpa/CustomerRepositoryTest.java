package com.example.jpa;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional(Transactional.TxType.NEVER)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository testee;
    @Test
    public void testInsert(){
        Customer saved = testee.save(new Customer(98L,"Jack", "Jones"));
        assertThat(saved.getFirstName()).isEqualTo("Jack");
        assertThat(saved.getLastName()).isEqualTo("Jones");
        assertThat(saved.getId()).isEqualTo(98L);
    }

    @Test
    void testFindById() {
        testee.save(new Customer(98L,"Jack", "Jones"));
        testee.save(new Customer(99L,"Jack", "Jones"));
        List<Customer> customers = testee.findByLastName("Jones");
        assertThat(customers).hasSize(2);
        Iterable<Customer> all = testee.findAll();
        System.out.println("NUMBER OF RECORDS: " + Stream.of(all).count());
    }


}