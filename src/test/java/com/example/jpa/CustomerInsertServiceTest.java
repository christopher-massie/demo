package com.example.jpa;

import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerInsertServiceTest {

    @Mock
    private CSVReader csvReader;
    @Mock
    private CSVConverter csvConverter;
    @Mock
    private CustomerRepository customerRepository;

    private CustomerInsertService testee;

    @BeforeEach
    void setUp() {
        testee = new CustomerInsertService(csvReader, csvConverter, customerRepository);
    }

    @Test
    void getAllCustomers_readsFromSpecifiedCsv() throws Exception {
        testee.getAllCustomers("filepath");
        verify(csvReader).parse("filepath");
    }

    @Test
    void getAllCustomers_callsDtoConverterForAllRecordsInCSV() throws Exception {
        List<CSVRecord> records = TestCSVRecords.getMultipleParsedRecords().getRecords();
        when(csvReader.parse(any())).thenReturn(records);

        List<CustomerDto> anyPath = testee.getAllCustomers("any path");
        verify(csvConverter, times(2)).convertToDto(any());
    }

    @Test
    void getAllCustomer_returnsCustomerDtosForAllRecordsInCSV() throws Exception {
        when(csvReader.parse(any())).thenReturn(TestCSVRecords.getMultipleParsedRecords().getRecords());
        CustomerDto customerDto1 = new CustomerDto(1L, "Timmy", "Tommy");
        CustomerDto customerDto2 = new CustomerDto(2L, "Billy", "Bongo");

        when(csvConverter.convertToDto(any()))
                .thenReturn(customerDto1)
                .thenReturn(customerDto2);

        List<CustomerDto> results = testee.getAllCustomers("dsfh");

        assertThat(results).containsExactlyInAnyOrder(customerDto1, customerDto2);
    }

    @Test
    void saveAllCustomers_convertsAllCustomerDtosToEntities() {
        CustomerDto customerDto = new CustomerDto(1L, "Timmy", "Tommy");
        Customer expectedCustomer = DtoConverter.convertDtoToEntity(customerDto);

        testee.saveAllCustomers(List.of(customerDto));

        verify(customerRepository).save(expectedCustomer);
    }

    @Test
    void saveAllCustomers_returnsTheSavedCustomers() {
        CustomerDto customerDto1 = new CustomerDto(1L, "Timmy", "Tommy");
        CustomerDto customerDto2 = new CustomerDto(1L, "Billy", "Bongo");
        Customer customer1 = DtoConverter.convertDtoToEntity(customerDto1);
        Customer customer2 = DtoConverter.convertDtoToEntity(customerDto2);

        when(customerRepository.save(any())).thenReturn(customer1).thenReturn(customer2);

        List<Customer> savedCustomers = testee.saveAllCustomers(List.of(customerDto1, customerDto2));
        assertThat(savedCustomers).containsExactlyInAnyOrder(customer1, customer2);
    }
}