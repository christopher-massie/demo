package com.example.jpa;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DtoConverterTest {

    @Test
    void testConvertDtoToEntity() {
        Customer expected = new Customer(1L,"Jack","Jones");
        Customer actual = DtoConverter.convertDtoToEntity(new CustomerDto(1L, "Jack", "Jones"));

        assertThat(actual).isEqualTo(expected);
    }


}